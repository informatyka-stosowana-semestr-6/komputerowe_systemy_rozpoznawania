package calculations;

import prepare_data.Article;
import prepare_data.Characteristic;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CharacteristicsExtraction {
    private CharacteristicsExtraction() {

    }

    public static Characteristic c1 (String article){
        Characteristic calc = null;
        return calc;
    }

    public static Characteristic c2 (String article){
        Characteristic calc = null;
        return calc;
    }

    public static Characteristic c3 (Article article){
        return new Characteristic("c3", (double) article.body.length());
    }

    public static Characteristic c4 (Article article){
        int sum = 0;
        for (String word: article.words) {
                sum += word.length();
        }
        return  new Characteristic("c4", (double) sum / (double) article.words.size());
    }

    public static Characteristic c5 (Article article){
        List<String> distinctElements =  article.words.stream()
                .distinct()
                .collect(Collectors.toList());
        return new Characteristic("c5", (double) distinctElements.size());
    }

    public static Characteristic c6 (Article article){
        int upperCase = 0;
        for (String word: article.words) {
            if(Character.isUpperCase(word.charAt(0))){
                upperCase++;
            }
        }
        return  new Characteristic("c6", (double) upperCase);
    }

    public static Characteristic c7 (Article article){
        int longWords = 0;
        for (String word: article.words) {
            if(word.length()<3){
                longWords++;
            }
        }
        return  new Characteristic("c7", (double) longWords);
    }

    public static Characteristic c8 (Article article){
        return new Characteristic("c8", (double) article.words.size());
    }

    public static Characteristic c9 (Article article){
        int longWords = 0;
        for (String word: article.words) {
            if(word.length()>8){
                longWords++;
            }
        }
        return  new Characteristic("c9", (double) longWords);
    }

    public static Characteristic c10 (Article article){
        int upperCase = 0;
        List<String> upperCaseChar = new ArrayList<>();
        for (String word: article.words) {
            if(Character.isUpperCase(word.charAt(0))){
                upperCaseChar.add(String.valueOf(word.charAt(0)));
            }
        }
        //unikalne litery
        Map<String,Long> collect = upperCaseChar.stream()
                .collect( Collectors.groupingBy( Function.identity(), Collectors.counting() ));

        //sortowanie
        LinkedHashMap<String, Long> countByWordSorted = collect.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> {
                            throw new IllegalStateException();
                        },
                        LinkedHashMap::new
                ));
        return  new Characteristic("c10", getFirstKey(countByWordSorted) + " " + getFirstValue(countByWordSorted));
//        return  new Characteristic("c10", getLastKey(countByWordSorted) + " " + getLastValue(countByWordSorted));
    }

    private static String getLastKey(LinkedHashMap<String, Long> lhm){
        int count = 1;
        for (Map.Entry<String, Long> it : lhm.entrySet()) {
            if (count == lhm.size()) {
                return it.getKey();
            }
            count++;
        }
        return "";
    }

    private static Long getLastValue(LinkedHashMap<String, Long> lhm){
        int count = 1;
        for (Map.Entry<String, Long> it : lhm.entrySet()) {
            if (count == lhm.size()) {
                return it.getValue();
            }
            count++;
        }
        return Long.valueOf(0);
    }

    // getFirst() method to get first element from
    // java LinkedHashMap
    private static String getFirstKey(LinkedHashMap<String, Long> lhm){
        int count = 1;
        for (Map.Entry<String, Long> it :
                lhm.entrySet()) {
            if (count == 1) {
                return it.getKey();
            }
            count++;
        }
        return "";
    }

    private static Long getFirstValue(LinkedHashMap<String, Long> lhm){
        int count = 1;
        for (Map.Entry<String, Long> it :
                lhm.entrySet()) {
            if (count == 1) {
                return it.getValue();
            }
            count++;
        }
        return Long.valueOf(0);
    }
}
