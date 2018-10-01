public class Camel extends Animal implements Herbivore{
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

    @Override
    public String goGrazing(){
        return "I am Grazing !";
    }

    @Override
    public String eat(){
        return goGrazing();
    }
}
