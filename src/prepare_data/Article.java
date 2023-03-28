package prepare_data;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Article implements Serializable {
//    public List<String> topics;
    public String title = "";
    public List<String> places;
    public List<String> words;
    public String date = "";
    public String body = "";

    public Article() {
    }

    public void setWords() {
        this.words = Arrays.asList(body.replaceAll("[^a-zA-Z \n]", "").split("\\s+"));
    }
}
