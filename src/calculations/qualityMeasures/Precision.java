package calculations.qualityMeasures;

public class Precision {
    private String placeName;
    private int numberOfArticles = 0;
    private int properClassifiedArticle = 0;
    private double precision;

    public double getPrecision() {
        return precision;
    }

    public Precision(String placeName) {
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
    public void calculatePrecision(){
        this.precision = (double) this.properClassifiedArticle / this.numberOfArticles;
    }
}
