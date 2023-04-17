package calculations;

import prepare_data.Article;

import java.util.*;
import java.util.function.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Characteristics {
    final private List<String> characteristicsNamesList;
    final private Map<String, Function<Article, ?>> functionMap = new HashMap<>();  // question mark allows to return all values (e.g. str or int)
    private final List<String> continentNames = List.of("Europe", "European", "America", "American", "Asia", "Asian");
    private final List<String> cityNames = List.of("Berlin", "Frankfurt", "Bonn", "Leverkusen", "Nuremberg", "Hanover",
            "Weisbaden", "Stuttgart", "Monachium", "Tokyo", "Yokohama", "Ottawa", "Paris", "Lyon", "Torronto", "London",
            "Manchester", "Liverpool", "Birmingham", "Washington", "New York", "Boston", "Los Angeles");

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

    private double c2(Article article) {
        /*
         Stosunek liczby wystąpień słów kluczowych do długości tekstu.
         */
        List<String> words = List.of(article.getBody().toLowerCase().split("\\W+"));
        List<String> keyWords =  new ArrayList<>();
        keyWords.addAll(this.continentNames);
        keyWords.addAll(this.cityNames);
        int numberOfUniqueWord = 0;

        for (String word : words) {
            for(String keyWord: keyWords){
                if (word.equals(keyWord.toLowerCase())) {
                    numberOfUniqueWord++;
                }
            }
        }
        return (double)numberOfUniqueWord / this.c8(article);
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
        Map<String, Integer> wordsOccurrence = new HashMap<>();
        List<String> words = List.of(article.getBody().toLowerCase().split("\\W+"));

        for (String word : words) {
            if (wordsOccurrence.containsKey(word)) {
                int lastValue = wordsOccurrence.get(word);
                wordsOccurrence.put(word, lastValue + 1);
            } else {
                wordsOccurrence.put(word, 1);
            }
        }
        int uniqueValues = 0;
        for (int value :
                wordsOccurrence.values()) {
            if (value == 1) {
                uniqueValues += 1;
            }
        }
        return uniqueValues;
    }

    private String c6(Article article) {
        /*
        Pierwsze wystąpienie we fragmencie tekstu nazwy kontynentu lub jego
        mieszkańca.
         */
        // split string to words with lowercase
        List<String> words = List.of(article.getBody().toLowerCase().split("\\W+"));
        for (String word : words) {
            for (String keyWord : this.continentNames) {
                if (word.equals(keyWord.toLowerCase())) {
                    return keyWord;
                }
            }
        }
        return "";
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

    private String c10(Article article) {
        /*
        Najliczniej występująca nazwa własna miasta bądź regionu ze zbioru C10
        w pierwszych pięciu zdaniach artykułu.
         */
        // Lower all words
        List<String> words = List.of(article.getBody().toLowerCase().split("\\W+"));
        List<String> keyWordsOccurrence = new ArrayList<>();

        for (String word : words) {
            for (String keyWord : this.cityNames) {
                if (word.equals(keyWord.toLowerCase())) {
                    keyWordsOccurrence.add(keyWord);
                }
            }
        }

        int maxCount = 0;
        String mostFrequentElement = "";
        for (String keyWord : keyWordsOccurrence) {
            int count = Collections.frequency(keyWordsOccurrence, keyWord);
            if (count > maxCount) {
                maxCount = count;
                mostFrequentElement = keyWord;
            }
        }
        return mostFrequentElement;
    }

    public void addCharacteristicVectorToArticle(Article article) {
        List<Object> vector = new ArrayList<>();  // This allows to store all objects (e.g. int or str)

        for (String characteristic : this.characteristicsNamesList) {
            vector.add(this.functionMap.get(characteristic).apply(article));
        }
        article.setCharacteristicVector(vector);
    }

}
