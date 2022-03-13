package prepare_data;

import java.io.Serializable;
import java.util.List;

    public class Article implements Serializable {
        public String title = "";
        public String places = "";
        public String date = "";
        public String body = "";
        public String toString() {
            return "Article{" +
                    "\n title='" + title + '\'' +
                    "\n topics=" + places +
                    "\n places=" + date +
                    "\n places=" + body +
                    "\n}s";
        }

    }

//    public Article(String title, String originalText, List<String> topics, List<String> places) {
//        this.title = title;
////        titleTokens = TextTokenizer.tokenizeLowerCaseWithProperNouns(title);
//        this.originalText = originalText;
////        textTokens = TextTokenizer.tokenizeLowerCaseWithProperNouns(originalText);
//        this.topics = places;
//        this.places = places;
//    }
//

//
////    public void trim(Trimmer trimmer){
////        textTokens = trimmer.trim(textTokens);
////        titleTokens= trimmer.trim(titleTokens);
////    }
////
////    public void filter(){
////        textTokens = StopWordsUtil.filter(textTokens, StopWordsUtil.english());
////        titleTokens = StopWordsUtil.filter(titleTokens, StopWordsUtil.english());
////    }
//}
