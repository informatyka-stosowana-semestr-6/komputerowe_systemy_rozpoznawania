package calculations;

import prepare_data.Article;

import java.util.*;
import java.util.function.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Characteristics {
    final private List<String> characteristicsNamesList;
    final private Map<String, Function<Article, ?>> functionMap = new HashMap<>();  // question mark allows to return all values (e.g. str or int)
    // TODO C6
    private List<String> continentNames = List.of(new String[]{"Europe", "European", "America", "American", "Asia", "Asian"});

    public Characteristics(List<String> characteristicsNamesList) {
        this.characteristicsNamesList = characteristicsNamesList;
        this.functionMap.put(CharacteristicsEnum.C1.getValue(), this::c1);
        this.functionMap.put(CharacteristicsEnum.C2.getValue(), this::c2);
        this.functionMap.put(CharacteristicsEnum.C3.getValue(), this::c3);
        this.functionMap.put(CharacteristicsEnum.C4.getValue(), this::c4);
        this.functionMap.put(CharacteristicsEnum.C5.getValue(), this::c5);
        this.functionMap.put(CharacteristicsEnum.C6.getValue(), this::c6);
        this.functionMap.put(CharacteristicsEnum.C7.getValue(), this::c7);
        this.functionMap.put(CharacteristicsEnum.C8.getValue(), this::c8);
        this.functionMap.put(CharacteristicsEnum.C9.getValue(), this::c9);
        this.functionMap.put(CharacteristicsEnum.C10.getValue(), this::c10);
    }

    private int c1(Article article) {
        /*
         Liczba całkowita reprezentująca największą ilość wyrazów w pierwszych 5 zdaniach.
         */
        int result = 0;
        List<String> sentences = List.of(article.getBody().split("(?<=[.!?])\\s+"));
        for (int i = 0; i < 5; i++) {
            if (sentences.size() > i) {
                result += List.of(sentences.get(i).split("\\s+")).size();
            }
        }
        return result;
    }

    private int c2(Article article) {
        /*
         Stosunek liczby wystąpień słów kluczowych do długości tekstu.
         */
        throw new UnsupportedOperationException("Not implemented yet.");
//        return 0;
    }

    private int c3(Article article) {
        /*
        Długość artykułu. (ilość liter)
         */
        return article.getBody().replaceAll("\\s+", "").replaceAll("[^\\p{L}]", "").length();
    }

    private float c4(Article article) {
        /*
        Średnia dlugość słowa.
        */
        String[] sentences = article.getBody().split("[.!?]");

        List<String> words = new ArrayList<>();
        for (String zdanie : sentences) {
            String[] wordsFromSentence = zdanie.trim().split("\\s+");
            words.addAll(Arrays.asList(wordsFromSentence));
        }
        int lettersLen = 0;
        for (String word : words) {
            lettersLen += word.length();
        }
        return (float) lettersLen / words.size();
    }

    private int c5(Article article) {
        /*
        Liczba unikalnych słów.
         */
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    private int c6(Article article) {
        /*
        Pierwsze wystąpienie we fragmencie tekstu nazwy kontynentu lub jego
        mieszkańca.
         */
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    private int c7(Article article) {
        /*
        Liczba słów nie przekraczająca 3 znaków.
         */
        int wordNumber = 0;
        Pattern pattern = Pattern.compile("\\b\\w{1,3}\\b");

        for (String sentence : article.getBody().split("[.!?]")) {
            Matcher matcher = pattern.matcher(sentence);
            while (matcher.find()) {
                wordNumber++;
            }
        }
        return wordNumber;
    }

    private int c8(Article article) {
        /*
        Liczba słów.
         */
        String[] sentences = article.getBody().split("[.!?]");

        List<String> words = new ArrayList<>();
        for (String sentence : sentences) {
            String[] wordsFromSentence = sentence.trim().split("\\s+");
            words.addAll(Arrays.asList(wordsFromSentence));
        }
        return words.size();
    }

    private int c9(Article article) {
        /*
         Liczba słów dłuższych niż 8 znaków.
         */
        String[] sentences = article.getBody().split("\\.\\s+");
        int wordNumber = 0;
        for (String sentence : sentences) {
            String[] words = sentence.split("\\s+");
            for (String word : words) {
                if (word.length() > 8) {
                    wordNumber++;
                }
            }
        }
        return wordNumber;
    }

    private int c10(Article article) {
        /*
        Najliczniej występująca nazwa własna miasta bądź regionu ze zbioru C10
        w pierwszych pięciu zdaniach artykułu.
         */
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void addCharacteristicVectorToArticle(Article article) {
        List<Object> vector = new ArrayList<>();  // This allows to store all objects (e.g. int or str)

        for (String characteristic : this.characteristicsNamesList) {
            vector.add(this.functionMap.get(characteristic).apply(article));
        }
        article.setCharacteristicVector(vector);
    }

}
