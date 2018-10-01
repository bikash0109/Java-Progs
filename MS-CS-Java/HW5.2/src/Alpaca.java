public class Alpaca extends Animal implements Herbivore{
    public Alpaca(String name) {
        super(name);
    }

    @Override
    public String animalSpecies() {
        return "Alpaca";
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
