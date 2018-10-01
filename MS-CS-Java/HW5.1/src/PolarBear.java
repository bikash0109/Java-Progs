public class PolarBear extends Animal implements Carnivore {
    public PolarBear(String name) {
        super(name);
    }

    @Override
    public String animalSpecies() {
        return "Polar Bear";
    }

    @Override
    public AnimalType animalType() {
        return AnimalType.Carnivore;
    }

    @Override
    public String home() {
        return "ice cave";
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
