import prepare_data.Article;
import prepare_data.Loader;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Loader loader = new Loader("data_test/");
        List<Article> articles = loader.loadData();
        System.out.println(articles.get(0));
    }
}
