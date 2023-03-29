package calculations;

import prepare_data.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

public class Characteristics {
    final private List<String> characteristicsNamesList;
    final private Map<String, Function<Article, ?>> functionMap = new HashMap<>();  // question mark allows to return all values (e.g. str or int)

    public Characteristics(List<String> characteristicsNamesList) {
        this.characteristicsNamesList = characteristicsNamesList;
        this.functionMap.put(CharacteristicsEnum.C1.getValue(), this::c1);
        this.functionMap.put(CharacteristicsEnum.C2.getValue(), this::c2);
        this.functionMap.put(CharacteristicsEnum.C3.getValue(), this::c3);
        this.functionMap.put(CharacteristicsEnum.C4.getValue(), this::c4);
        this.functionMap.put(CharacteristicsEnum.C5.getValue(), this::c5);
        this.functionMap.put(CharacteristicsEnum.C6.getValue(), this::c6);
        this.functionMap.put(CharacteristicsEnum.C7.getValue(), this::c7);
        this.functionMap.put(CharacteristicsEnum.C8.getValue(), this::c8);
        this.functionMap.put(CharacteristicsEnum.C9.getValue(), this::c9);
        this.functionMap.put(CharacteristicsEnum.C10.getValue(), this::c10);
    }

    private int c1(Article article) {
        return 1;
    }

    private int c2(Article article) {
        return 2;
    }

    private int c3(Article article) {
        return 3;
    }

    private int c4(Article article) {
        return 4;
    }

    private int c5(Article article) {
        return 5;
    }

    private int c6(Article article) {
        return 6;
    }

    private int c7(Article article) {
        return 7;
    }

    private int c8(Article article) {
        return 8;
    }

    private int c9(Article article) {
        return 9;
    }

    private int c10(Article article) {
        return 10;
    }

    public List<Object> getCharacteristicVector(Article article) {
        List<Object> vector = new ArrayList<>();  // This allows to store all objects (e.g. int or str)

        for (String characteristic : this.characteristicsNamesList) {
            vector.add(this.functionMap.get(characteristic).apply(article));
        }
        return vector;
    }

}
