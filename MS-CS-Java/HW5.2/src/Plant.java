import java.util.Random;

public abstract class Plant extends Zoo {
    private int plantId;
    private String plantName;
    private String plantLocation;

    public Plant() {
    }

    public Plant(String name, String plantLocation) {
        this.plantName = name;
        this.plantLocation = plantLocation;
        this.plantId = new Random().nextInt(1000000);
    }

    public String toString() {
        return "\nId: " + this.plantId + "\t-> Name: " + this.plantName + "\t-> Location: " + location()
                + "\n********************************************************************************************************************************\n";
    }

    public String getPlantName(){
        return this.plantName;
    }

    public String location(){
        return plantLocation;
    }

    public void setLocation(String newLocation){
        plantLocation = newLocation;
    }
}
