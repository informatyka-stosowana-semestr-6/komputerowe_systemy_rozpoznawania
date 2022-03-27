import calculations.DocumentsClassification;
import prepare_data.Article;
import prepare_data.LoadData;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //test
        DocumentsClassification documents = new DocumentsClassification();
        documents.calculateCharacteristics();

        System.out.println("Size" + documents.getArticles().size());
        for (Article article: documents.getArticles()) {
            System.out.println(article.toString());
        }

        //All
//        LoadData ld = new LoadData("data/");
    }
}
