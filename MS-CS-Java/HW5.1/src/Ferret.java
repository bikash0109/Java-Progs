public class Ferret extends Animal implements Carnivore {
    public Ferret(String name) {
        super(name);
    }

    @Override
    public String animalSpecies() {
        return "Ferret";
    }

    @Override
    public AnimalType animalType() {
        return AnimalType.Carnivore;
    }

    @Override
    public String home() {
        return "ferret cage";
    }

    @Override
    public String goHunting() {
        return "I am Hunting !";
    }

    @Override
    public String eat(){
        return goHunting();
    }
}
