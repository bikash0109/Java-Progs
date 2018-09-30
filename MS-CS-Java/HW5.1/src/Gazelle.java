// A gazelle class
public class Gazelle extends Animal {
    public Gazelle(String name) {
        super(name);
    }

    @Override
    public AnimalType animalType() {
        return AnimalType.Herbivore;
    }

    @Override
    public String animalSpecies() {
        return "Gazelle";
    }
}
