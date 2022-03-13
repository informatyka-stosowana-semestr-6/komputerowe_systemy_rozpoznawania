package prepare_data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LoadData {

    private List<Article> articles;
    private List<String> files_string;
    private String directoryPath;

    public LoadData(String directoryPath) throws IOException {
        setDirectoryPath(directoryPath);
    }

    private void setDirectoryPath(String directoryPath) throws IOException {
        this.directoryPath = directoryPath;
        articles = new ArrayList<>();

        File directory = new File(directoryPath);
        List<File> files = Arrays.asList(directory.listFiles());

        for (File f : files ) {
            System.out.println(f);
            System.out.println(Files.readString(f.toPath()));
            articles.addAll(SGAFileNormalizer.parseString(Files.readString(f.toPath())));

        }
        System.out.println(articles.size());
        System.out.println(articles.toString());
    }

}
