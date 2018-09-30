public class PolarBear extends Animal {
    public PolarBear(String name) {
        super(name);
    }

    @Override
    public String animalSpecies() {
        return "Polar Bear";
    }

    @Override
    public AnimalType animalType() {
        return AnimalType.Carnivore;
    }

    @Override
    public String home() {
        return "ice cave";
    }
}
