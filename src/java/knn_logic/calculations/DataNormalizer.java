package java.knn_logic.calculations;

import java.knn_logic.prepare_data.Article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataNormalizer {
    private ArrayList<Article> articles;

    public DataNormalizer(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public void normailize() {
        for (int characteristic_index = 0; characteristic_index < articles.get(0).getCharacteristicVector().size(); characteristic_index++) {
            List<Double> minMax;
            try {
                minMax = this.getMinMax(characteristic_index);
            }catch (NumberFormatException e){continue;}

            for (Article article: articles){
                article.getCharacteristicVector().set(characteristic_index, ( Double.parseDouble (article.getCharacteristicVector().get(characteristic_index).toString()) - minMax.get(0)) / (minMax.get(1) - minMax.get(0)));
            }
        }
    }

    private List<Double> getMinMax(int characteristic_index) {
        List<Double> characteristicVector = new ArrayList<>();
        if (!(articles.get(0).getCharacteristicVector().get(characteristic_index) instanceof Number)){
            throw new NumberFormatException ("Not implemented yet.");
        }

        for (Article article : this.articles) {
            characteristicVector.add(Double.parseDouble(article.getCharacteristicVector().get(characteristic_index).toString()));
        }
        return Arrays.asList(Collections.min(characteristicVector), Collections.max(characteristicVector));
    }

}
