package java.knn_gui;

public class KeyValuePair {
    private final String label;
    private final double value;

    public KeyValuePair(String key, double value) {
        this.label = key;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getKey() {
        return label;
    }

    public double getValue(){
        return value;
    }

    public String toString() {
        return label;
    }
}