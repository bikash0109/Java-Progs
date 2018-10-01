import java.util.Random;

// Abstract of an Animal, its basic methods
public abstract class Animal extends Zoo {
    private String animalName;

    public Animal() {
    }

    public Animal(String name) {
        this.animalName = name;
    }

    public String toString() {
        return "\nAnimal Species: " + animalSpecies() + "\t-> Name: " + animalName + "\t-> Type: " + animalType() + "\t-> Home: " + home()
                + "\n********************************************************************************************************************************\n";
    }

    public abstract String eat();

    public String goHome() {
        return "I am a  " + animalSpecies() +
                "\nMy name is " + animalName + ". I am in my " + home();
    }

    public String getName(){
        return this.animalName;
    }

    public String animalSpecies() {
        return "Unknown Animal";
    }

    public AnimalType animalType() {
        return AnimalType.UnknownFoodHabit;
    }

    public String home() {
        return "Zoo cell";
    }

    public Boolean isHome() {
        return new Random().nextBoolean();
    }

    public String location(){
        return animalType() == AnimalType.Carnivore ? "Hunting near river" : animalType() == AnimalType.Herbivore
                ? "Grazing on the hill top" : "In open range";
    }
}