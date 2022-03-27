package calculations;

import prepare_data.Article;
import prepare_data.LoadData;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collector;

public class DocumentsClassification{
    private List<Article> articles;

    public DocumentsClassification() throws IOException {

        LoadData ld = new LoadData("data_test/");
//        LoadData ld = new LoadData("data/");
        this.articles = ld.getArticles();
    }

    public List<Article> getArticles() {
        return articles;
    }
    public void calculateCharacteristics() {
        for (Article article: articles) {
//            article.characteristics.add(CharacteristicsExtraction.c1(article.body));
//            article.characteristics.add(CharacteristicsExtraction.c2(article.body));
//            article.characteristics.add(CharacteristicsExtraction.c3(article.body));
//            article.characteristics.add(CharacteristicsExtraction.c4(article.body));
//            article.characteristics.add(CharacteristicsExtraction.c5(article.body));
//            article.characteristics.add(CharacteristicsExtraction.c6(article.body));
//            article.characteristics.add(CharacteristicsExtraction.c7(article.body));
            article.characteristics.add(CharacteristicsExtraction.c8(article));
//            article.characteristics.add(CharacteristicsExtraction.c9(article.body));
//            article.characteristics.add(CharacteristicsExtraction.c10(article.body));
        }
    }
}
