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
//            article.characteristics.add(CharacteristicsExtraction.c1(article.body));//1. Najwięcej wyrazów w pierwszych 5 zdaniach.
//            article.characteristics.add(CharacteristicsExtraction.c2(article.body));//2. Stosunek liczby wystąpień słów kluczowych do długości tekstu.
            article.characteristics.add(CharacteristicsExtraction.c3(article));//3. Długość artykułu.
            article.characteristics.add(CharacteristicsExtraction.c4(article));//4. Średnia dlugość słowa.
            article.characteristics.add(CharacteristicsExtraction.c5(article));//5. Liczba unikalnych słów.
            article.characteristics.add(CharacteristicsExtraction.c6(article));//6. Liczba słów zaczynająca się dużą literą.
            article.characteristics.add(CharacteristicsExtraction.c7(article));//7. Liczba słów nie przekraczająca 3 znaków.
            article.characteristics.add(CharacteristicsExtraction.c8(article));//8. Liczba słów.
            article.characteristics.add(CharacteristicsExtraction.c9(article));//9. Liczba słów dłuższych niż 8 znaków.
            article.characteristics.add(CharacteristicsExtraction.c10(article));//10. Litera na którą zaczyna się najwięcej słów z wielkiej litery
        }
    }
}
