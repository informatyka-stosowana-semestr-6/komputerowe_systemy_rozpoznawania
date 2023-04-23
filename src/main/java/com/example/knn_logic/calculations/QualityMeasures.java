package com.example.knn_logic.calculations;

import com.example.knn_logic.prepare_data.Article;

import java.util.ArrayList;
import java.util.List;

public class QualityMeasures {
    private List<Article> articles;
    private List<String> places = List.of("usa", "uk", "canada", "west-germany", "france", "japan");

    public QualityMeasures(List<Article> articles){
        this.articles = articles;
    }

    public double calculatePrecision(String place){
        // TODO: 16.04.2023 Calculate precision
        int truePositive = 0;
        int falsePositive = 0;
        for (Article article : articles){
            if (article.getArticleType().equals("test") && article.getPlace().equals(place)){
                if (article.getPlace().equals(article.getPredictedPlace())){
                    truePositive++;
                }
                else{
                    falsePositive++;
                }
            }
        }
        return (double) truePositive /(truePositive+falsePositive);
    }

    public double calculateRecall(String place){
        int truePositive = 0;
        int falseNegative = 0;
        for (Article article : articles){
            if (article.getArticleType().equals("test") && article.getPlace().equals(place)){
                if (article.getPlace().equals(article.getPredictedPlace())){
                    truePositive++;
                }
            }
            if (article.getArticleType().equals("test") && !article.getPlace().equals(place)){
                if (article.getPredictedPlace().equals(place)){
                    falseNegative++;
                }
            }
        }
        return (double) truePositive /(truePositive+falseNegative);
    }

    public double calculateF1(String place){
        double precision = calculatePrecision(place);
        double recall = calculateRecall(place);

        return (2 * precision * recall)/(precision + recall);
    }

    public double calculateAccuracy(){
        // TODO: 16.04.2023 Calculate accuracy
        int truePositive = 0;
        int allArticles = 0;
        for (Article article : articles){
            if (article.getArticleType().equals("test")){
                if (article.getPlace().equals(article.getPredictedPlace())){
                    truePositive++;
                }
                allArticles++;
            }
        }
        System.out.println("True positive: " + truePositive);
        System.out.println("All articles: " + allArticles);
        return (double) truePositive/(allArticles);
    }

    public double calculatePrecisionMean(ConfusionMatrix cm){
        double licznik = 0.0;
        double mianownik = 0.0;
        for(int i = 0; i < 6; i++){
            double precision = calculatePrecision(places.get(i));
            if (Double.isNaN(precision)){
                precision = 0.0;
            }
            licznik += precision * cm.getConfusionMatrix()[i][i];
            mianownik += cm.getConfusionMatrix()[i][i];
        }
        System.out.println(licznik);
        System.out.println(mianownik);
        return licznik/mianownik;
    }

    public double calculateRecallMean(ConfusionMatrix cm){
        double licznik = 0.0;
        double mianownik = 0.0;
        for(int i = 0; i < 6; i++){
            double recall = calculateRecall(places.get(i));
            if (Double.isNaN(recall)){
                recall = 0.0;
            }
            licznik += recall * cm.getConfusionMatrix()[i][i];
            mianownik += cm.getConfusionMatrix()[i][i];
        }
        System.out.println(licznik);
        System.out.println(mianownik);
        return licznik/mianownik;
    }

    public double calculateF1Mean(ConfusionMatrix cm){
        double licznik = 0.0;
        double mianownik = 0.0;
        for(int i = 0; i < 6; i++){
            double f1 = calculateF1(places.get(i));
            if (Double.isNaN(f1)){
                f1 = 0.0;
            }
            licznik += f1 * cm.getConfusionMatrix()[i][i];
            mianownik += cm.getConfusionMatrix()[i][i];
        }
        System.out.println(licznik);
        System.out.println(mianownik);
        return licznik/mianownik;
    }
}
