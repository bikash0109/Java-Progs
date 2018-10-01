public class Skunk extends Animal implements Carnivore, Herbivore{
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

    @Override
    public String goGrazing(){
        return "I am Grazing !";
    }

    @Override
    public String goHunting() {
        return "I am Hunting !";
    }

    @Override
    public String eat(){
        return goHunting() + " and " + goGrazing();
    }
}
