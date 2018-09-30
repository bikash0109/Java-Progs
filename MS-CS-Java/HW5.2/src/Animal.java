import java.util.Random;

// Abstract of an Animal, its basic methods
public abstract class Animal extends Zoo implements Carnivore, Herbivore{
    private int animalId;
    private String animalName;

    public Animal() {
    }

    public Animal(String name) {
        this.animalName = name;
        this.animalId = new Random().nextInt(1000000);
    }

    public String toString() {
        return "\nId: " + this.animalId + "\t-> Animal Species: " + animalSpecies() + "\t-> Name: " + animalName +
                "\t-> Type: " + animalType() + "\t-> Home: " + home()
                + "\n********************************************************************************************************************************\n";
    }

    public String eat() {
        return "I am a  " + animalSpecies() +
                "\nMy name is " + animalName + (animalType() == AnimalType.Carnivore ?
                "\n" + this.goHunting() : animalType() == AnimalType.Herbivore ? "\n" + this.goGrazing() :
                "\n" + this.goHunting() +" and " + this.goGrazing());
    }

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

    @Override
    public String goGrazing(){
        return "I am Grazing !";
    }

    @Override
    public String goHunting() {
        return "I am Hunting !";
    }
}