package com.example.knn_logic.calculations;

import com.example.knn_logic.prepare_data.Article;

import java.util.List;

public class QualityMeasures {
    private List<Article> articles;

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

    public double calculateF1(){
        // TODO: 16.04.2023 Calculate F1

        return 0;
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
}
