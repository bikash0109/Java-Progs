public class Skunk extends Animal{
    public Skunk(String name) {
        super(name);
    }

    @Override
    public String animalSpecies() {
        return "Skunk";
    }

    @Override
    public AnimalType animalType() {
        return AnimalType.Omnivore;
    }

    @Override
    public String home() {
        return "woodpiles";
    }
}
