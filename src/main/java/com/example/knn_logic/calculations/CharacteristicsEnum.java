package com.example.knn_logic.calculations;

public enum CharacteristicsEnum {
    C1("c1"),
    C2("c2"),
    C3("c3"),
    C4("c4"),
    C5("c5"),
    C6("c6"),
    C7("c7"),
    C8("c8"),
    C9("c9"),
    C10("c10");

    private final String value;

    CharacteristicsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
