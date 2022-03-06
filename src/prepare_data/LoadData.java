package prepare_data;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LoadData {

//    private List<Article> articles;
    private List<String> articles;
    private String directoryPath;

    public LoadData(String directoryPath) {
        setDirectoryPath(directoryPath);
    }

    private void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
        articles = new ArrayList<>();

        File directory = new File(directoryPath);
        List<File> files = Arrays.asList(directory.listFiles());

        for (File f : files ) {
            System.out.println(f);

        }

    }

}
