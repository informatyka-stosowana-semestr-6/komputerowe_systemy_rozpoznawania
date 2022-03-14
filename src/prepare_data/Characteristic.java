package prepare_data;

public class Characteristic {
    private String name = "";
    private String valueSting;
    private Double  valueDouble;

    public Characteristic(String name, String value){
        this.name = name;
        this.valueSting = value;
    }
    public Characteristic(String name, Double value){
        this.name = name;
        this.valueDouble = value;
    }

    public <T> T getValue(){
        if(valueDouble != null){
            return (T) valueDouble;
        }else{
            return (T) valueSting;
        }
    }


    public String getName() {
        return name;
    }



}
