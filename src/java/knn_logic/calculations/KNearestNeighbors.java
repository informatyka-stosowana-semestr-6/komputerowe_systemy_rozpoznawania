package java.knn_logic.calculations;

import java.knn_logic.prepare_data.Article;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class KNearestNeighbors {
    List<Article> traineeArticles;
    List<Article> testArticles;
    final private BiFunction<List<?>, List<?>, Double> distanceFunction;
    int k;

    public KNearestNeighbors(List<Article> traineeArticles, List<Article> testArticles, String distanceType, int k) {
        this.traineeArticles = traineeArticles;
        this.testArticles = testArticles;
        this.k = k;
        if (Objects.equals(distanceType, "Czebyszewa")) this.distanceFunction = this::czebyszewDistance;
        else if (Objects.equals(distanceType, "Manhattan")) this.distanceFunction = this::manhattanDistance;
        else this.distanceFunction = this::euclideanDistance;
    }

    private double euclideanDistance(List<?> vector1, List<?> vector2) {
        double sumOfSquares = 0.0;
        for (double distance : this.getDistanceVector(vector1, vector2)) {
            sumOfSquares += distance * distance;
        }

        return Math.sqrt(sumOfSquares);
    }

    private double manhattanDistance(List<?> vector1, List<?> vector2) {
        double sumOfDistances = 0.0;
        for (double distance : this.getDistanceVector(vector1, vector2)) {
            sumOfDistances += Math.abs(distance);
        }
        return sumOfDistances;
    }

    private double czebyszewDistance(List<?> vector1, List<?> vector2) {
        double maxAbsDistance = 0;
        List<Double> distanceVector = this.getDistanceVector(vector1, vector2);
        for (Double distance : distanceVector) {
            if (Math.abs(distance) > maxAbsDistance) {
                maxAbsDistance = Math.abs(distance);
            }
        }
        return maxAbsDistance;
    }

    private double nGramsMeasurementDistance(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0) {
            return 1;  // Distance is 1 when one of the string is empty
        }
        // Assign word1 and word2 to s1, s2 due to length
        String s1;
        String s2;
        if (word1.length() < word2.length()) {
            s1 = word1;
            s2 = word2;
        } else {
            s1 = word2;
            s2 = word1;
        }

        // Assign n
        int n = Math.min(s1.length(), 3);
        double factor = 1f / (s1.length() - n + 1);
        int containsSum = 0;
        for (int i = 0; i < s1.length() - n + 1; i++) {
            String sub = s1.substring(i, i + 3);
            if (s2.contains(sub)) {
                containsSum += 1;
            }
        }
        return 1 - (factor * containsSum);
    }

    private List<Double> getDistanceVector(List<?> vector1, List<?> vector2) {
        if (vector1.size() != vector2.size()) {
            throw new IndexOutOfBoundsException("Vector sizes are different");
        }
        List<Double> distanceVector = new ArrayList<>();

        for (int i = 0; i < vector1.size(); i++) {
            if ((vector1.get(i) instanceof Number) && (vector2.get(i) instanceof Number)) {
                double diff = ((Number) vector1.get(i)).doubleValue() - ((Number) vector2.get(i)).doubleValue();
                distanceVector.add(diff);
            } else {
                distanceVector.add(this.nGramsMeasurementDistance(vector1.get(i).toString(), vector2.get(i).toString()));
            }
        }
        return distanceVector;
    }
    private void normalize(){
        DataNormalizer normalizer = new DataNormalizer((ArrayList<Article>) this.testArticles);
        DataNormalizer normalizer_2 = new DataNormalizer((ArrayList<Article>) this.traineeArticles);
        normalizer.normailize();
        normalizer_2.normailize();
    }
    public void predict(boolean isNormalized) {
        if (isNormalized) {this.normalize();}
        for (Article testArticle : this.testArticles) {
            for (Article traineeArticle : this.traineeArticles) {
                double distance = this.distanceFunction.apply(testArticle.getCharacteristicVector(), traineeArticle.getCharacteristicVector());
                testArticle.addDistanceVector(traineeArticle, distance);
            }
            testArticle.sortDistanceVector();
            List<Article> kNearestArticles = new ArrayList<>(testArticle.getDistancesVector().keySet()).subList(0, this.k);
            Map<String, Integer> kNearestPlaces = new LinkedHashMap<>();
            for (Article article : kNearestArticles) {
                String place = article.getPlace();
                if (kNearestPlaces.containsKey(place)) {
                    int lastValue = kNearestPlaces.get(place);
                    kNearestPlaces.put(place, lastValue + 1);
                } else {
                    kNearestPlaces.put(place, 1);
                }
            }
            Map<String, Integer> sortedKNearestPlaces = new LinkedHashMap<>();
            kNearestPlaces.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(x -> sortedKNearestPlaces.put(x.getKey(), x.getValue()));
            testArticle.setPredictedPlace(new ArrayList<>(sortedKNearestPlaces.keySet()).get(sortedKNearestPlaces.size() - 1));
//            System.out.println(testArticle.getPlace());
//            System.out.println(testArticle.getPredictedPlace());
//            System.out.println("#######################################");
        }
    }

}
