package prepare_data;

import java.util.List;

public class Article {
    private String title;
    private String originalText;
    private List<String> topics;
    private List<String> places;
    private List<String> textTokens;
    private List<String> titleTokens;

    public Article(String title, String originalText, List<String> topics, List<String> places) {
        this.title = title;
//        titleTokens = TextTokenizer.tokenizeLowerCaseWithProperNouns(title);
        this.originalText = originalText;
//        textTokens = TextTokenizer.tokenizeLowerCaseWithProperNouns(originalText);
        this.topics = places;
        this.places = places;
    }

    public String toString() {
        return "Article{" +
                "\ntitle='" + title + '\'' +
                "\n topics=" + topics +
                "\n places=" + places +
                "\n}s";
    }

//    public void trim(Trimmer trimmer){
//        textTokens = trimmer.trim(textTokens);
//        titleTokens= trimmer.trim(titleTokens);
//    }
//
//    public void filter(){
//        textTokens = StopWordsUtil.filter(textTokens, StopWordsUtil.english());
//        titleTokens = StopWordsUtil.filter(titleTokens, StopWordsUtil.english());
//    }
}
