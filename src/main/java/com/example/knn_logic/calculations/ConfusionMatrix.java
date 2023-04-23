package com.example.knn_logic.calculations;


import com.example.knn_logic.prepare_data.Article;

import java.util.ArrayList;
import java.util.List;

public class ConfusionMatrix {

    private int[][] confusionMatrix = new int[6][6];
    private final List<String > placeNames = new ArrayList<>();
    private List<Article> articles;
    public ConfusionMatrix(List<Article> articles){
        this.articles = articles;

        placeNames.add("usa");
        placeNames.add("uk");
        placeNames.add("canada");
        placeNames.add("west-germany");
        placeNames.add("france");
        placeNames.add("japan");
    }

    public int[][] getConfusionMatrix() {
        return confusionMatrix;
    }

    public void calculateMatrix(){
        for (int i =0; i< 6; i++) {  // Original places
            for(int j = 0; j< 6; j++){  // Predicted places (or on other order XD)
                int count = 0;
                for (Article article: this.articles){
                    if(article.getPlace().equals(placeNames.get(i))) {
                        if (article.getPredictedPlace().equals(placeNames.get(j))) {
                            count++;
                        }
                    }
                }
                this.confusionMatrix[i][j] = count;
            }
        }
    }
}
