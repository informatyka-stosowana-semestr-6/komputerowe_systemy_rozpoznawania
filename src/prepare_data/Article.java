package prepare_data;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Article implements Serializable {
    //    public List<String> topics;
    private String title = "";
    private final List<String> places;
    private List<String> words;
    private String date = "";
    private String body = "";

    public Article(String title, List<String> places, String date, String body) {
        this.title = title;
        this.places = places;
        this.date = date;
        this.body = body;
        this.setWords();
    }

    private void setWords() {
        this.words = Arrays.asList(body.replaceAll("[^a-zA-Z \n]", "").split("\\s+"));
    }

    public String getTitle() {
        return title;
    }

    public List<String> getPlaces() {
        return places;
    }

    public List<String> getWords() {
        return words;
    }

    public String getDate() {
        return date;
    }

    public String getBody() {
        return body;
    }
}
