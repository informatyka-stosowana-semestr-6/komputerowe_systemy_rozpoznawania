package prepare_data;

import java.io.Serializable;
import java.util.List;

public class Article implements Serializable {
//    public List<String> topics;
    public String title = "";
    public List<String> places;
    public String date = "";
    public String body = "";

    public Article() {
    }

    public String toString() {
        return "Article{" +
                "\n title='" + title + '\'' +
//                "\n topics=" + topics +
                "\n places=" + places +
                "\n body=" + body +
                "\n}s";
    }

}
