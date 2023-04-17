package calculations.qualityMeasures;

public class Recall {
    private String placeName;
    private int numberOfArticles = 0;
    private int properClassifiedArticle = 0;
    private double recall;

    public double getRecall() {
        return recall;
    }

    public Recall(String placeName) {
        this.placeName = placeName;
    }

    public void addNumberOfArticles(int number){
        this.numberOfArticles += number;
    }
    public void addProperClassifiedArticle(int number){
        this.properClassifiedArticle += number;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public int getNumberOfArticles() {
        return numberOfArticles;
    }

    public void setNumberOfArticles(int numberOfArticles) {
        this.numberOfArticles = numberOfArticles;
    }

    public int getProperClassifiedArticle() {
        return properClassifiedArticle;
    }

    public void setProperClassifiedArticle(int properClassifiedArticle) {
        this.properClassifiedArticle = properClassifiedArticle;
    }
    public void calculateRecall(){
        this.recall = (double) this.properClassifiedArticle / this.numberOfArticles;
    }
}
