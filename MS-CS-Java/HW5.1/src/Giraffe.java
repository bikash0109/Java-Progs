// A giraffe class
public class Giraffe extends Animal {
    public Giraffe(String name) {
        super(name);
    }

    @Override
    public AnimalType animalType() {
        return AnimalType.Herbivore;
    }

    @Override
    public String animalSpecies() {
        return "Giraffe";
    }
}