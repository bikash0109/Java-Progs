public class Aardvark extends Animal implements Carnivore, Herbivore{
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

    @Override
    public String goHunting() {
        return "I am Hunting !";
    }

    @Override
    public String goGrazing(){
        return "I am Grazing !";
    }

    @Override
    public String eat(){
        return goHunting() + " and " + goGrazing();
    }
}
