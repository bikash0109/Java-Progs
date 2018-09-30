public class Ferret extends Animal {
    public Ferret(String name) {
        super(name);
    }

    @Override
    public String animalSpecies() {
        return "Ferret";
    }

    @Override
    public AnimalType animalType() {
        return AnimalType.Carnivore;
    }

    @Override
    public String home() {
        return "ferret cage";
    }
}
