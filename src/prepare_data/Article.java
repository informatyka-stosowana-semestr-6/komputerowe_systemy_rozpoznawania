package prepare_data;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Article implements Serializable {
//    public List<String> topics;
    public String title = "";
    public List<String> places;
    public List<String> words;
    public List<Characteristic> characteristics = new ArrayList<>();
    public String date = "";
    public String body = "";

//    static SnowballStemmer stemmer = new SnowballStemmer() {
//        @Override
//        public boolean stem() {
//            return false;
//        }
//    };

    public Article(){}

    public String toString() {
        return "Article{" +
                "\n title='" + title + '\'' +
//                "\n topics=" + topics +
                "\n places=" + places +
//                "\n body=" + body +
//                "\n words=" + words.size() +
                toStringCharacteristics() +
                "\n} " ;
    }

    public String toStringCharacteristics() {
        String result = "";
        for (Characteristic str: characteristics) {
            result += str.toString();
        }
        return result;
    }


//    public void extractWords(Trimmer trimmer){
//        words = trimmer.trim(textTokens);
//    }

    public void split() {
        this.words = Arrays.asList(body.replaceAll("[^a-zA-Z \n]", "").split("\\s+"));
    }

//    public String trim(String input){
//        stemmer.setCurrent(input);
//        stemmer.stem();
//        return stemmer.getCurrent();
//    }
//
//    public List<String> trim(List<String> input){
//        return input.stream()
//                .map(this::trim)
//                .collect(Collectors.toList());
//    }


}
