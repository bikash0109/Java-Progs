public class Camel extends Animal {
    public Camel(String name) {
        super(name);
    }

    @Override
    public String animalSpecies() {
        return "Camel";
    }

    @Override
    public AnimalType animalType() {
        return AnimalType.Herbivore;
    }

    @Override
    public String home() {
        return "stables";
    }
}
