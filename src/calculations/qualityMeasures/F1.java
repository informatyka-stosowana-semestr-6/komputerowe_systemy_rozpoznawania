package calculations.qualityMeasures;

public class F1 {
    private String placeName;
    private double f1;

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getF1() {
        return f1;
    }

    public void setF1(double f1) {
        this.f1 = f1;
    }

    public F1(String placeName) {
        this.placeName = placeName;
    }
    public void calculateF1(double precision, double recall){
        this.f1 = 2 * (precision * recall) / (precision + recall);
    }
}
