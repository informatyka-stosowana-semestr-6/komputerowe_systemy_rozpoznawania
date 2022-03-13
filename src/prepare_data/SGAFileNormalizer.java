package prepare_data;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class SGAFileNormalizer {
    static Pattern EXTRACTION_PATTERN = Pattern
            .compile("<DATE>(.*?)</DATE>.*?<PLACES>(.*?)</PLACES>.*?<TITLE>(.*?)</TITLE>.*?<BODY>(.*?)</BODY>", Pattern.DOTALL);

    public static ArrayList<Article> parseString(String reuters_file) {
        ArrayList<Article> reuters_articles = new ArrayList<>();
        Matcher matcher = EXTRACTION_PATTERN.matcher(reuters_file);
        while (matcher.find()) {
            Article reuters = new Article();
            reuters.date = matcher.group(1);
            reuters.places = getSeparatedStringsPatternD(matcher.group(2).replaceAll("&lt;", "<"));
            reuters.title = matcher.group(3).replaceAll("&lt;", "<");
            reuters.body = matcher.group(4).replaceAll("&lt;", "<");
            reuters_articles.add(reuters);
        }
        return reuters_articles;
    }

    private static List<String> getSeparatedStringsPatternD(String match) {
        Matcher m = Pattern.compile("<D>(.+?)</D>").matcher(match);
        List<String> result = new ArrayList<>();
        while (m.find()) {
            result.add(m.group(1).replace("&lt;", "<"));
        }
        return result;
    }

}
