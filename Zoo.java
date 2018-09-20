import java.util.Random;
import java.util.Vector;

class Hunter {
    String animalName;
    Boolean isHunting;
}

class Grazer {
    String animalName;
    Boolean isGrazing;
}

abstract class Animal {
    Hunter hunter;
    Grazer grazer;
    private String name;
    private String type;
    public Boolean isHunter = false;

    public Animal(String name, String type) {
        this.name = name;
        this.type = type;
        if (type.equals("Carnivore")) {
            hunter = new Hunter();
            this.isHunter = true;
            setHunter();
        } else {
            grazer = new Grazer();
            setGrazer();
        }
    }

    public String speak() {
        return "I am a  " + animalName() +
                "\nMy name is " + name +
                "\nI am a " + type + "\n" + home();
    }

    public String speak(boolean raoming) {
        return "I am a  " + animalName() +
                "\nMy name is " + name + (isHunter ?
                "\nI love hunting ! Fresh meat..." : "\nI love grazing ! Fresh plants...");
    }

    public abstract String animalName();

    public Boolean isHome() {
        return isHunter ? !hunter.isHunting : !grazer.isGrazing;
    }

    public void sendHome() {
        if (isHunter)
            hunter.isHunting = false;
        else
            grazer.isGrazing = false;
        System.out.println(this.speak());
    }

    public void sendHunting() {
        hunter.isHunting = false;
        System.out.println(this.speak(true));
    }

    public void sendGrazing() {
        grazer.isGrazing = false;
        System.out.println(this.speak(true));
    }

    public Hunter setHunter() {
        hunter.animalName = animalName();
        hunter.isHunting = new Random().nextBoolean();
        return hunter;
    }

    public Grazer setGrazer() {
        grazer.animalName = animalName();
        grazer.isGrazing = new Random().nextBoolean();
        return grazer;
    }

    public String home() {
        return "I am in my Zoo cell";
    }
}

class Tiger extends Animal {

    public Tiger(String name, String type) {
        super(name, type);
    }

    @Override
    public String animalName() {
        return "Tiger";
    }

    @Override
    public String home() {
        return isHome() ? "\nI am home. \nI am in my den." : "\nI am not home";
    }
}

class Lion extends Animal {
    public Lion(String name, String type) {
        super(name, type);
    }

    @Override
    public String animalName() {
        return "Lion";
    }

    @Override
    public String home() {
        return isHome() ? "\nI am home. \nI am in my cave." : "\nI am not home";
    }
}

class Giraffe extends Animal {
    public Giraffe(String name, String type) {
        super(name, type);
    }

    @Override
    public String animalName() {
        return "Giraffe";
    }
}

class Gazelle extends Animal {
    public Gazelle(String name, String type) {
        super(name, type);
    }

    @Override
    public String animalName() {
        return "Gazelle";
    }
}

public class Zoo {
    public static void zooKeeperAdmin(Vector<Animal> animals) {
        for (Animal animal : animals) {
            System.out.println("Hey " + animal.animalName() + " are you home ? \n" + animal.animalName() + ":" + (animal.isHome() ? "Yes" : "No"));
            if (!animal.isHome()) {
                if (animal.isHunter) {
                    System.out.println("Hey " + animal.animalName() +
                            " are you hunting ? \n" + animal.animalName() + ":" + (animal.hunter.isHunting ? "Yes" : "No"));
                    if (animal.hunter.isHunting) {
                        System.out.println("Go home ... !");
                        animal.sendHome();
                    } else {
                        System.out.println("Hey " + animal.animalName() +
                                " are you hungry ? \n" + animal.animalName() + ":" + (animal.hunter.isHunting ? "No" : "Yes"));
                        System.out.println("Go hunting ... !");
                        animal.sendHunting();
                    }
                } else {
                    System.out.println("Hey " + animal.animalName() +
                            " are you grazing ? \n" + animal.animalName() + ":" + (animal.grazer.isGrazing ? "Yes" : "No"));
                    if (animal.grazer.isGrazing) {
                        System.out.println("Go home ... !");
                        animal.sendHome();
                    } else {
                        System.out.println("Hey " + animal.animalName() +
                                " are you hungry ? \n" + animal.animalName() + ":" + (animal.grazer.isGrazing ? "No" : "Yes"));
                        System.out.println("Go grazing ... !");
                        animal.sendGrazing();
                    }
                }
            } else
                System.out.println(animal.speak());
            System.out.println("\n*************************************************************");
        }
    }

    public static void main(String[] args) {
        Vector<Animal> animals = new Vector<>();
        animals.add(new Tiger("Sher Khan", "Carnivore"));
        animals.add(new Lion("Simba", "Carnivore"));
        animals.add(new Giraffe("Girfy", "Herbivore"));
        animals.add(new Gazelle("Buckshot", "Herbivore"));
        zooKeeperAdmin(animals);
    }
}