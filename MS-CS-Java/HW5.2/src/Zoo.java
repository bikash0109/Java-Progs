/*
 * Program Name: Zoo.java
 *
 * Version :  1
 *
 * @author: Bikash Roy
 * @author: Tanay Bhardwaj
 *
 * This program uses the concept of inheritance through Abstract class and interfaces to manage a zoo.
 */

import java.util.Vector;

// Zoo class, where a zookeeper asks for animal status and directs it to home.
public class Zoo {

    public static void sendHome(Animal animal) {
        System.out.println(animal.goHome());
    }

    public static void goEat(Animal animal) {
        System.out.println(animal.eat());
    }

    public static void movePlant(Plant plant, String newLocation){
        plant.setLocation(newLocation);
    }

    public static String plantLocation(Plant plant){
        return plant.location();
    }

    public static String animalLocation(Animal animal){
        return animal.location();
    }

    public static void zooKeeperAnimalAdminTests(Vector<Animal> animals) {
        for (Animal animal : animals) {
            boolean isHome = animal.isHome();
            System.out.println("Hey " + animal.animalSpecies() + " are you home ? \n" + animal.animalSpecies() + ":" +
                    (isHome ? "Yes" : "No"));
            if (!isHome) {
                System.out.println("Hey " + animal.animalSpecies() +
                        " are you " + (animal.animalType() == AnimalType.Carnivore ? "hunting" :
                        animal.animalType() == AnimalType.Herbivore ? "grazing" : "hunting or grazing") + " ? \n"
                        + animal.animalSpecies() + ":" + (isHome ? "No" : "Yes"));
                System.out.println("Go home ... !");
                sendHome(animal);
            } else {
                System.out.println("Hey " + animal.animalSpecies() +
                        " are you hungry ? \n" + animal.animalSpecies() + ": Yes");
                System.out.println(animal.animalType() == AnimalType.Carnivore ? "Go hunting ... !" :
                        animal.animalType() == AnimalType.Herbivore ? "Go Grazing ... !" : "Go Hunting or Grazing ... !");
                goEat(animal);
                System.out.println("Location : " + animalLocation(animal));
            }
            System.out.println("*************************************************************\n");
        }
    }

    public static void zooKeeperPlantAdmiTests(Vector<Plant> plants){
        for(Plant plant : plants){
            System.out.println(plant.getPlantName() + ", what is your location ? \nPlant : " + plantLocation(plant));
            System.out.println("Move to new location: \"Near the fountain..!\"");
            movePlant(plant, "Near the fountain..!");
            System.out.println(plant.getPlantName() + " : " + plantLocation(plant));
        }
    }

    public static void main(String[] args) {
        Vector<Zoo> zooElements = new Vector<>();
        Vector<Animal> animals = new Vector<>();
        Vector<Plant> plants = new Vector<>();
        Vector<AquaticAnimal> aquaticAnimals = new Vector<>();
        animals.add(new Aardvark("Aardy"));
        animals.add(new Alpaca("Alpacy"));
        animals.add(new PolarBear("Polly"));
        plants.add(new Palm("PalmTree", "Near pool"));
        aquaticAnimals.add(new Fish("GoldFish"));
        zooElements.addAll(animals);
        zooElements.addAll(plants);
        zooElements.addAll(aquaticAnimals);
        System.out.println("All elements in my zoo :");
        System.out.println(zooElements);
        System.out.println("\n######################################################################");
        System.out.println("Zoo keeper admin tests ... ! \n");
        System.out.println("Animal tests ... ! \n");
        zooKeeperAnimalAdminTests(animals);
        System.out.println("Plant tests ... ! \n");
        zooKeeperPlantAdmiTests(plants);
    }
}