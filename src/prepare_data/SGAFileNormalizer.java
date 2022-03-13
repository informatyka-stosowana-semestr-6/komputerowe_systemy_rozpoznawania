package prepare_data;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SGAFileNormalizer {
    static Pattern EXTRACTION_PATTERN = Pattern
            .compile("<DATE>(.*?)</DATE>(?s).*<PLACES>(.*?)</PLACES>(?s).*<TITLE>(.*?)</TITLE>(?s).*<BODY>(.*?)</BODY>");
//            .compile("<DATE>(.*?)</DATE>.*?<PLACES>(.*?)</PLACES>.*?<TITLE>(.*?)</TITLE>.*?<BODY>(.*?)</BODY>");

    public static ArrayList<Article> parseString(String reuters_file) {
        ArrayList<Article> reuters_articles= new ArrayList<>();
        Matcher matcher = EXTRACTION_PATTERN.matcher(reuters_file);
        while (matcher.find()) {
            Article reuters = new Article();
            reuters.date = matcher.group(1);
            reuters.places = matcher.group(2).replaceAll("&lt;", "<");
            reuters.title = matcher.group(3).replaceAll("&lt;", "<");
            reuters.body = matcher.group(4).replaceAll("&lt;", "<");
            reuters_articles.add(reuters);
        }
        return reuters_articles;
    }

}
