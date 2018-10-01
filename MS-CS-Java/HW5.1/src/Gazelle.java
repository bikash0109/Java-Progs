// A gazelle class
public class Gazelle extends Animal implements Herbivore {
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

    @Override
    public String goGrazing(){
        return "I am Grazing !";
    }

    @Override
    public String eat(){
        return goGrazing();
    }
}
