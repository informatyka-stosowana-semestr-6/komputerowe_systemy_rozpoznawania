package java.knn_logic.prepare_data;


import java.io.Serializable;
import java.util.*;

public class Article implements Serializable, Cloneable {
    //    public List<String> topics;
    private String title = "";
    private final String place;
    private List<String> words;
    private String date = "";
    private String body = "";
    private List<Object> characteristicVector;
    private String predictedPlace;
    private String articleType;
    private Map<Article, Double> distancesVector = new LinkedHashMap<>();

    public Article(String title, String place, String date, String body) {
        this.title = title;
        this.place = place;
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

    public String getPlace() {
        return place;
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

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public List<Object> getCharacteristicVector() {
        return characteristicVector;
    }

    public void setCharacteristicVector(List<Object> characteristicVector) {
        this.characteristicVector = characteristicVector;
    }

    public String getPredictedPlace() {
        return predictedPlace;
    }

    public void setPredictedPlace(String predictedPlace) {
        this.predictedPlace = predictedPlace;
    }

    public Map<Article, Double> getDistancesVector() {
        return distancesVector;
    }
    public void sortDistanceVector()
    {
        // Sortujemy mapę po wartościach
        Map<Article, Double> sortedMap = new LinkedHashMap<>();
        this.getDistancesVector().entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        this.distancesVector = sortedMap;
    }
    public void addDistanceVector(Article article, double distance) {
        this.distancesVector.put(article, distance);
    }

    @Override
    public Article clone() {
        try {
            Article clone = (Article) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
