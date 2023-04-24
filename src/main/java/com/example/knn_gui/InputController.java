package com.example.knn_gui;

import com.example.knn_logic.calculations.*;
import com.example.knn_logic.prepare_data.Article;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.CheckComboBox;

import java.net.URL;
import java.util.*;
import java.util.function.UnaryOperator;

public class InputController implements Initializable {
    @FXML
    public Label cm_USA_USA;
    @FXML
    public Label cm_UK_USA;
    @FXML
    public Label cm_Canada_USA;
    @FXML
    public Label cm_Germany_USA;
    @FXML
    public Label cm_France_USA;
    @FXML
    public Label cm_Japan_USA;
    @FXML
    public Label cm_USA_UK;
    @FXML
    public Label cm_UK_UK;
    @FXML
    public Label cm_Canada_UK;
    @FXML
    public Label cm_Germany_UK;
    @FXML
    public Label cm_France_UK;
    @FXML
    public Label cm_Japan_UK;
    @FXML
    public Label cm_USA_Canada;
    @FXML
    public Label cm_UK_Canada;
    @FXML
    public Label cm_Canada_Canada;
    @FXML
    public Label cm_Germany_Canada;
    @FXML
    public Label cm_France_Canada;
    @FXML
    public Label cm_Japan_Canada;
    @FXML
    public Label cm_USA_Germany;
    @FXML
    public Label cm_UK_Germany;
    @FXML
    public Label cm_Canada_Germany;
    @FXML
    public Label cm_Germany_Germany;
    @FXML
    public Label cm_France_Germany;
    @FXML
    public Label cm_Japan_Germany;
    @FXML
    public Label cm_USA_France;
    @FXML
    public Label cm_UK_France;
    @FXML
    public Label cm_Canada_France;
    @FXML
    public Label cm_Germany_France;
    @FXML
    public Label cm_France_France;
    @FXML
    public Label cm_Japan_France;
    @FXML
    public Label cm_USA_Japan;
    @FXML
    public Label cm_UK_Japan;
    @FXML
    public Label cm_Canada_Japan;
    @FXML
    public Label cm_Germany_Japan;
    @FXML
    public Label cm_France_Japan;
    @FXML
    public Label cm_Japan_Japan;

    @FXML
    private TextField knn_value;
    @FXML
    private TextField seed_value;
    @FXML
    private ChoiceBox<KeyValuePair> test_train_ratio;
    @FXML
    private ChoiceBox<String> metric;
    @FXML
    private CheckBox seed_checkbox;
    @FXML
    private Button start_button;
    @FXML
    private CheckBox data_normalized;
    @FXML
    private CheckComboBox<String> characteristic;


    //Quality Measures
    @FXML
    private TextField accuracy;
    @FXML
    //Precision
    private Label precision_USA;
    @FXML
    private Label precision_UK;
    @FXML
    private Label precision_Canada;
    @FXML
    private Label precision_Germany;
    @FXML
    private Label precision_France;
    @FXML
    private Label precision_Japan;
    @FXML
    private Label precision_Mean;

    //Recall
    @FXML
    private Label recall_USA;
    @FXML
    private Label recall_UK;
    @FXML
    private Label recall_Canada;
    @FXML
    private Label recall_Germany;
    @FXML
    private Label recall_France;
    @FXML
    private Label recall_Japan;
    @FXML
    private Label recall_Mean;
    @FXML
    private GridPane cm_grid;

    //F1
    @FXML
    private Label f1_USA;
    @FXML
    private Label f1_UK;
    @FXML
    private Label f1_Canada;
    @FXML
    private Label f1_Germany;
    @FXML
    private Label f1_France;
    @FXML
    private Label f1_Japan;
    @FXML
    private Label f1_Mean;

    public Button getStart_button() {
        return start_button;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("[0-9]*")) {
                return change;
            }

            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        TextFormatter<String> textFormatter2 = new TextFormatter<>(filter);
        knn_value.setTextFormatter(textFormatter);
        seed_value.setTextFormatter(textFormatter2);


        seed_checkbox.setSelected(true);
        data_normalized.setSelected(true);
        seed_value.setText("12");

        test_train_ratio.getItems().addAll(Arrays.asList(
                new KeyValuePair("80/20", 0.8),
                new KeyValuePair("70/30", 0.7),
                new KeyValuePair("60/40", 0.6),
                new KeyValuePair("50/50", 0.5),
                new KeyValuePair("40/60", 0.4),
                new KeyValuePair("10/90", 0.1)));

        metric.getItems().addAll(Arrays.asList("Euklidesowa", "Manhattan", "Czebyszewa"));
        characteristic.getItems().addAll(Arrays.asList(CharacteristicsEnum.C1.getValue(),
                CharacteristicsEnum.C2.getValue(),
                CharacteristicsEnum.C3.getValue(),
                CharacteristicsEnum.C4.getValue(),
                CharacteristicsEnum.C5.getValue(),
                CharacteristicsEnum.C6.getValue(),
                CharacteristicsEnum.C7.getValue(),
                CharacteristicsEnum.C8.getValue(),
                CharacteristicsEnum.C9.getValue(),
                CharacteristicsEnum.C10.getValue()));

        characteristic.getCheckModel().checkAll();
        metric.getSelectionModel().selectFirst();
        knn_value.setText("3");
        test_train_ratio.getSelectionModel().selectFirst();

    }

    private List<String> getCharacteristics() {
        return characteristic.getCheckModel().getCheckedItems();
    }

    @FXML
    protected void onButtonStart(List<Article> articles) {
        Characteristics characteristics = new Characteristics(this.getCharacteristics());
        System.out.println(this.getCharacteristics());
        for (Article article : articles) {
            characteristics.addCharacteristicVectorToArticle(article);
        }
        start_button.setDisable(true);
        double splitPoint = test_train_ratio.getValue().getValue();
        int splitIndex = (int) (articles.size() * splitPoint);
        if (seed_checkbox.isSelected()) {
            Collections.shuffle(articles, new Random(Integer.parseInt(seed_value.getText())));
        } else {
            Collections.shuffle(articles);
        }

        List<Article> traineeArticles = new ArrayList<>(articles.subList(0, splitIndex));
        List<Article> testArticles = new ArrayList<>(articles.subList(splitIndex, articles.size()));

        for (Article article : traineeArticles) {
            article.setArticleType("train");
        }
        for (Article article : testArticles) {
            article.setArticleType("test");
        }

        KNearestNeighbors knn = new KNearestNeighbors(traineeArticles, testArticles, metric.getValue(), Integer.parseInt(knn_value.getText()));
        knn.predict(data_normalized.isSelected());
        start_button.setDisable(false);



        // Confusion Matrix
        ConfusionMatrix confusionMatrixObj = new ConfusionMatrix(testArticles);
        confusionMatrixObj.calculateMatrix();
        int[][] confusionMatrixTable = confusionMatrixObj.getConfusionMatrix();
        int columns = cm_grid.getColumnCount();
        for (int colIndex = 1; colIndex < columns; colIndex++) {
            for (int rowIndex = 1; rowIndex < cm_grid.getRowCount(); rowIndex++) {
                Node node = cm_grid.getChildren().get((12 + 6 * (colIndex - 1) + (rowIndex)) - 1);
                if (node instanceof Label) {
                    ((Label) node).setText(String.valueOf(confusionMatrixTable[colIndex - 1][rowIndex - 1]));
                }
            }
        }



        QualityMeasures qm = new QualityMeasures(articles);
//        System.out.println(articles);
//        System.out.println(qm.calculateAccuracy());
        accuracy.setText((Double.toString(qm.calculateAccuracy())));

        //Zmienne pomocnicze
        double precision_USA_value = (double) Math.round(qm.calculatePrecision("usa") * 100000) / 100000;
        double precision_UK_value = (double) Math.round(qm.calculatePrecision("uk") * 100000) / 100000;
        double precision_Canada_value = (double) Math.round(qm.calculatePrecision("canada") * 100000) / 100000;
        double precision_Germany_value = (double) Math.round(qm.calculatePrecision("west-germany") * 100000) / 100000;
        double precision_France_value = (double) Math.round(qm.calculatePrecision("france") * 100000) / 100000;
        double precision_Japan_value = (double) Math.round(qm.calculatePrecision("japan") * 100000) / 100000;

        precision_USA.setText(Double.toString(precision_USA_value));
        precision_UK.setText(Double.toString(precision_UK_value));
        precision_Canada.setText(Double.toString(precision_Canada_value));
        precision_Germany.setText(Double.toString(precision_Germany_value));
        precision_France.setText(Double.toString(precision_France_value));
        precision_Japan.setText(Double.toString(precision_Japan_value));
//        precision_Mean.setText(Double.toString((double) Math.round(100000 * (precision_USA_value + precision_UK_value + precision_Canada_value + precision_Germany_value + precision_France_value + precision_Japan_value) / 6) / 100000));
        precision_Mean.setText(Double.toString((double) Math.round(qm.calculatePrecisionMean(confusionMatrixObj)* 100000) / 100000));

        //Zmienne pomocnicze
        double recall_USA_value = (double) Math.round(qm.calculateRecall("usa") * 100000) / 100000;
        double recall_UK_value = (double) Math.round(qm.calculateRecall("uk") * 100000) / 100000;
        double recall_Canada_value = (double) Math.round(qm.calculateRecall("canada") * 100000) / 100000;
        double recall_Germany_value = (double) Math.round(qm.calculateRecall("west-germany") * 100000) / 100000;
        double recall_France_value = (double) Math.round(qm.calculateRecall("france") * 100000) / 100000;
        double recall_Japan_value = (double) Math.round(qm.calculateRecall("japan") * 100000) / 100000;

        recall_USA.setText(Double.toString(recall_USA_value));
        recall_UK.setText(Double.toString(recall_UK_value));
        recall_Canada.setText(Double.toString(recall_Canada_value));
        recall_Germany.setText(Double.toString(recall_Germany_value));
        recall_France.setText(Double.toString(recall_France_value));
        recall_Japan.setText(Double.toString(recall_Japan_value));
//        recall_Mean.setText(Double.toString((double) Math.round(100000 * (recall_USA_value + recall_UK_value + recall_Canada_value + recall_Germany_value + recall_France_value + recall_Japan_value) / 6) / 100000));
        recall_Mean.setText(Double.toString((double) Math.round(qm.calculateRecallMean(confusionMatrixObj)* 100000) / 100000));



        //Zmienne pomocnicze
        double f1_USA_value = (double) Math.round(100000 * 2 * ((precision_USA_value * recall_USA_value) / (precision_USA_value + recall_USA_value))) / 100000;
        double f1_UK_value = (double) Math.round(100000 * 2 * ((precision_UK_value * recall_UK_value) / (precision_UK_value + recall_UK_value))) / 100000;
        double f1_Canada_value = (double) Math.round(100000 * 2 * ((precision_Canada_value * recall_Canada_value) / (precision_Canada_value + recall_Canada_value))) / 100000;
        double f1_Germany_value = (double) Math.round(100000 * 2 * ((precision_Germany_value * recall_Germany_value) / (precision_Germany_value + recall_Germany_value))) / 100000;
        double f1_France_value = (double) Math.round(100000 * 2 * ((precision_France_value * recall_France_value) / (precision_France_value + recall_France_value))) / 100000;
        double f1_Japan_value = (double) Math.round(100000 * 2 * ((precision_Japan_value * recall_Japan_value) / (precision_Japan_value + recall_Japan_value))) / 100000;

        f1_USA.setText(Double.toString(f1_USA_value));
        f1_UK.setText(Double.toString(f1_UK_value));
        f1_Canada.setText(Double.toString(f1_Canada_value));
        f1_Germany.setText(Double.toString(f1_Germany_value));
        f1_France.setText(Double.toString(f1_France_value));
        f1_Japan.setText(Double.toString(f1_Japan_value));
//        f1_Mean.setText(Double.toString((double) Math.round(100000 * (f1_USA_value + f1_UK_value + f1_Canada_value + f1_Germany_value + f1_France_value + f1_Japan_value) / 6) / 100000));
        f1_Mean.setText(Double.toString((double) Math.round(qm.calculateF1Mean(confusionMatrixObj)* 100000) / 100000));


    }

    @FXML
    protected void onCheckboxClicked() {
        seed_value.setDisable(!seed_checkbox.isSelected());
    }


}

