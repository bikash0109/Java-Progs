/*
 * Program Name: Zoo.java
 *
 * Version :  1
 *
 * @author: Bikash Roy
 * @author: Tanay Bhardwaj
 *
 * This program uses the concept of inheritance through Abstract class and interface to manage a zoo.
 */

import java.util.Vector;

// Zoo class, where a zookeeper asks for animal status and directs it to home.
public class Zoo {
    public static void zooKeeperAdminTest(Vector<Animal> animals) {
        for (Animal animal : animals) {
            boolean isHome = animal.isHome();
            System.out.println("Hey " + animal.animalSpecies() + " are you home ? \n" + animal.animalSpecies() + ":" + (isHome ? "Yes" : "No"));
            if (!isHome) {
                System.out.println("Hey " + animal.animalSpecies() +
                        " are you " + (animal.animalType() == AnimalType.Carnivore ? "hunting" : animal.animalType() == AnimalType.Herbivore
                        ? "grazing" : "hunting or grazing") + " ? \n" + animal.animalSpecies() + ":" + (isHome ? "No" : "Yes"));
                System.out.println("Go home ... !");
                System.out.println(animal.goHome());
            } else {
                System.out.println("Hey " + animal.animalSpecies() +
                        " are you hungry ? \n" + animal.animalSpecies() + ": Yes");
                System.out.println(animal.animalType() == AnimalType.Carnivore ? "Go hunting ... !" : animal.animalType() == AnimalType.Herbivore
                        ? "Go Grazing ... !" : "Go Hunting or Grazing ... !");
                animal.eat();
                animal.location();
            }
            System.out.println("*************************************************************\n");
        }
    }

    public static void main(String[] args) {
        Vector<Animal> animals = new Vector<>();
        animals.add(new Tiger("Sher Khan"));
        animals.add(new Lion("Simba"));
        animals.add(new Giraffe("Girfy"));
        animals.add(new Gazelle("Buckshot"));
        animals.add(new Aardvark("Aardy"));
        animals.add(new Alpaca("Alpacy"));
        animals.add(new Camel("Outh"));
        animals.add(new Ferret("Ferry"));
        animals.add(new PolarBear("Polly"));
        animals.add(new Skunk("Skunky"));
        System.out.println("\nAll animals in my zoo: " + animals);
        System.out.println("\n########################################################################");
        System.out.println("\nZoo keeper admin tests.....\n");
        zooKeeperAdminTest(animals);
    }
}