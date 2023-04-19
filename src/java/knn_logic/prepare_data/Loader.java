package java.knn_logic.prepare_data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Loader {
    final private String directoryPath;

    public Loader(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public List<Article> loadData() throws IOException {
        List<Article> articles = new ArrayList<>();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        assert files != null;
        for (File file : files) {
            articles.addAll(SGAFileExtractor.parseFile(Files.readString(file.toPath())));
        }
        return articles;
    }


}
