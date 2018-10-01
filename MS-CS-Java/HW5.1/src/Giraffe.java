// A giraffe class
public class Giraffe extends Animal implements Herbivore{
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

    @Override
    public String goGrazing(){
        return "I am Grazing !";
    }

    @Override
    public String eat(){
        return goGrazing();
    }
}