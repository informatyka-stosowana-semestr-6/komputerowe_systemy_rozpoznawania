package calculations;

import calculations.qualityMeasures.F1;
import calculations.qualityMeasures.Precision;
import calculations.qualityMeasures.Recall;
import prepare_data.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CalculateQualityMeasures {
    private List<Article> articles;
    private double accuracy;
    private List<Precision> precision = new ArrayList<>();
    private List<Recall> recall = new ArrayList<>();
    private List<F1> f1 = new ArrayList<>();
    private double f1AllClasses;
    private int numberOfArticles;
    private final List<String> places = new ArrayList<>();

    public CalculateQualityMeasures(List<Article> articles) {
        this.articles = articles;
        this.numberOfArticles = articles.size();
        this.setPlaces();
        for (String place : this.places) {
            precision.add(new Precision(place));
            recall.add(new Recall(place));
            f1.add(new F1(place));
        }
    }
    private void setPlaces(){
        places.add("usa");
        places.add("west-germany");
        places.add("france");
        places.add("uk");
        places.add("canada");
        places.add("japan");
    }
    public void calculateAccuracy() {
        int properClassifiedArticle = 0;

        for (Article article : this.articles) {
            if (Objects.equals(article.getPredictedPlace(), article.getPlace())) {
                properClassifiedArticle++;
            }
        }
        this.accuracy = (double) properClassifiedArticle / this.numberOfArticles;
    }

    public void calculatePrecision() {
        for (Article article : this.articles) {
            for (Precision precisionObject : this.precision) {
                if (Objects.equals(article.getPredictedPlace(), precisionObject.getPlaceName())) {
                    precisionObject.addNumberOfArticles(1);
                    if (Objects.equals(article.getPredictedPlace(), article.getPlace())) {
                        precisionObject.addProperClassifiedArticle(1);
                    }
                    break;
                }
            }
        }
        for (Precision precisionObject : this.precision) {
            precisionObject.calculatePrecision();
        }
    }
    public void calculateRecall(){
        for (Article article : this.articles) {
            for (Recall recallObject : this.recall) {
                if (Objects.equals(article.getPlace(), recallObject.getPlaceName())) {
                    recallObject.addNumberOfArticles(1);
                    if (Objects.equals(article.getPredictedPlace(), article.getPlace())) {
                        recallObject.addProperClassifiedArticle(1);
                    }
                    break;
                }
            }
        }
        for (Recall recallObject : this.recall) {
            recallObject.calculateRecall();
        }
    }

    public void calculateF1(){
        for(int i = 0; i < this.f1.size(); i++){
            this.f1.get(i).calculateF1(precision.get(i).getPrecision(), recall.get(i).getRecall());
        }
    }
    public void calculateF1ForAllClasses(){
        double meter = 0; // licznik
        double denominator = 0; // mianownik

        for (int i = 0; i < this.f1.size(); i++){
            meter += this.f1.get(i).getF1() * this.precision.get(i).getProperClassifiedArticle();
            denominator += this.precision.get(i).getProperClassifiedArticle();
        }
        this.f1AllClasses = meter / denominator;
    }

    public void calculateMeasures() {
        this.calculateAccuracy();
        this.calculatePrecision();
        this.calculateRecall();
        this.calculateF1();
        this.calculateF1ForAllClasses();
    }
}
