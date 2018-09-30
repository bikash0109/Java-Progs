// A lion class
public class Lion extends Animal {

    public Lion(String name) {
        super(name);
    }

    @Override
    public String animalSpecies() {
        return "Lion";
    }

    @Override
    public AnimalType animalType() {
        return AnimalType.Carnivore;
    }

    @Override
    public String home() {
        return "cave";
    }
}