// A lion class
public class Lion extends Animal implements Carnivore {

    public Lion(String name) {
        super(name);
    }

    @Override
    public String animalSpecies() {
        return "Lion";
    }

    @Override
    public AnimalType animalType() {
        return AnimalType.Carnivore;
    }

    @Override
    public String home() {
        return "cave";
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