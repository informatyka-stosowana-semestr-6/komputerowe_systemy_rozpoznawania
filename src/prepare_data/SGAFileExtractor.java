package prepare_data;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

public class SGAFileExtractor {
    static Pattern EXTRACTION_PATTERN = Pattern
            .compile("<DATE>(.*?)</DATE>.*?<PLACES>(.*?)</PLACES>.*?<TITLE>(.*?)</TITLE>.*?<BODY>(.*?)</BODY>", Pattern.DOTALL);

    public static ArrayList<Article> parseFile(String fileData) {
        ArrayList<Article> allArticles = new ArrayList<>();
        Matcher matcher = EXTRACTION_PATTERN.matcher(fileData);
        while (matcher.find()) {
            Article article = new Article();
            article.date = matcher.group(1);
            article.places = getSeparatedPlaces(matcher.group(2).replaceAll("&lt;", "<"));
            article.title = matcher.group(3).replaceAll("&lt;", "<");
            article.body = matcher.group(4).replaceAll("&lt;", "<");
            article.setWords();

            allArticles.add(article);
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
