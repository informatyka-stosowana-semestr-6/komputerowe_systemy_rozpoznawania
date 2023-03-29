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
        Loader loader = new Loader("data_test/");
        List<Article> articles = loader.loadData();
        // There we can define all characteristics C1 - C10
        Characteristics characteristics = new Characteristics(new ArrayList<>(Arrays.asList(CharacteristicsEnum.C1.getValue(), CharacteristicsEnum.C2.getValue())));

        for (Article article : articles) {
            System.out.println(characteristics.getCharacteristicVector(article));
            // TODO maybe we should store this vector inside article object
        }
    }
}
