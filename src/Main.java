import calculations.Characteristics;
import calculations.CharacteristicsEnum;
import calculations.KNearestNeighbors;
import prepare_data.Article;
import prepare_data.Loader;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        ////////////////////////////////
        // This is only for debug mode
        ///////////////////////////////
        List<String> debugImplementedCharacteristics = new ArrayList<>(
                Arrays.asList(CharacteristicsEnum.C1.getValue(),
                        CharacteristicsEnum.C3.getValue(),
                        CharacteristicsEnum.C4.getValue(),
                        CharacteristicsEnum.C6.getValue(),
                        CharacteristicsEnum.C7.getValue(),
                        CharacteristicsEnum.C8.getValue(),
                        CharacteristicsEnum.C9.getValue(),
                        CharacteristicsEnum.C10.getValue()));
        /////////////
        // Main
        /////////////
        Loader loader = new Loader("data/");
        List<Article> articles = loader.loadData();
        // There we can define all characteristics C1 - C10
        Characteristics characteristics = new Characteristics(debugImplementedCharacteristics);
        for (Article article : articles) {characteristics.addCharacteristicVectorToArticle(article);}

        double splitPoint = 0.8;
        int splitIndex = (int) (articles.size() * splitPoint);
        Collections.shuffle(articles, new Random(12));

        List<Article> traineeArticles = new ArrayList<>(articles.subList(0, splitIndex));
        List<Article> testArticles = new ArrayList<>(articles.subList(splitIndex, articles.size()));

        KNearestNeighbors knn = new KNearestNeighbors(traineeArticles, testArticles, "euclidean", 3);
        knn.predict();

    }
}
