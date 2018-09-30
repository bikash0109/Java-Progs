public class Aardvark extends Animal {
    public Aardvark(String name) {
        super(name);
    }

    @Override
    public String animalSpecies() {
        return "Aardvark";
    }

    @Override
    public AnimalType animalType() {
        return AnimalType.Omnivore;
    }

    @Override
    public String home() {
        return "burrow";
    }
}
