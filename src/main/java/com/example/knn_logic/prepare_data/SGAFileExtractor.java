package com.example.knn_logic.prepare_data;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

public class SGAFileExtractor {
    private static final List<String> validPlaces = List.of("west-germany", "usa", "france", "uk", "canada", "japan");

    static Pattern EXTRACTION_PATTERN = Pattern
            .compile("<DATE>(.*?)</DATE>.*?<PLACES>(.*?)</PLACES>.*?<TITLE>(.*?)</TITLE>.*?<BODY>(.*?)</BODY>", Pattern.DOTALL);

    public static ArrayList<Article> parseFile(String fileData) {
        ArrayList<Article> allArticles = new ArrayList<>();
        Matcher matcher = EXTRACTION_PATTERN.matcher(fileData);
        while (matcher.find()) {
            String date = matcher.group(1);
            List<String> places = getSeparatedPlaces(matcher.group(2).replaceAll("&lt;", "<"));
            String title = matcher.group(3).replaceAll("&lt;", "<");
            String body = matcher.group(4).replaceAll("&lt;", "<");
            if(places.size() == 1 && validPlaces.contains(places.get(0)))
            {
                String place = places.get(0);
                allArticles.add(new Article(title, place, date, body));
            }
        }
        return allArticles;
    }

    private static List<String> getSeparatedPlaces(String match) {
        Matcher m = Pattern.compile("<D>(.+?)</D>").matcher(match);
        List<String> separatedPlaces = new ArrayList<>();
        while (m.find()) {
            separatedPlaces.add(m.group(1).replace("&lt;", "<"));
        }
        return separatedPlaces;
    }

}
