// A tiger class
public class Tiger extends Animal implements Carnivore{

    public Tiger(String name) {
        super(name);
    }

    @Override
    public String animalSpecies() {
        return "Tiger";
    }

    @Override
    public AnimalType animalType() {
        return AnimalType.Carnivore;
    }

    @Override
    public String home() {
        return  "den";
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