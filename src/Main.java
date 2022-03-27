import calculations.DocumentsClassification;
import prepare_data.LoadData;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //test
        DocumentsClassification documents = new DocumentsClassification();
        documents.calculateCharacteristics();
        //All
//        LoadData ld = new LoadData("data/");
    }
}
