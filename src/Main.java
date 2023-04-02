import calculations.Characteristics;
import calculations.CharacteristicsEnum;
import prepare_data.Article;
import prepare_data.Loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        Loader loader = new Loader("data_test/");
        List<Article> articles = loader.loadData();
        // There we can define all characteristics C1 - C10
        Characteristics characteristics = new Characteristics(debugImplementedCharacteristics);

        for (Article article : articles) {
            characteristics.addCharacteristicVectorToArticle(article);
            System.out.println(article.getCharacteristicVector());
        }
    }
}
