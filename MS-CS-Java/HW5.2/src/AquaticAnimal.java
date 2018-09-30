import java.util.Random;

public abstract class AquaticAnimal extends Zoo {
    private int aquaId;
    private String aquaName;
    private int numberOfAquaAnimals;

    public AquaticAnimal() {
    }

    public AquaticAnimal(String name) {
        this.aquaName = name;
        this.aquaId = new Random().nextInt(1000000);
        this.numberOfAquaAnimals = new Random().nextInt(10000);
    }

    public String toString() {
        return "\nSchool Id: " + this.aquaId + "\t-> Name: " + this.aquaName + "\t-> Number of fishes is a school: " + numberOfAquaAnimals
                + "\n********************************************************************************************************************************\n";
    }

    public String getFishName(){
        return this.aquaName;
    }
}
