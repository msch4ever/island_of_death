package cz.los;

import cz.los.animals.Animal;
import cz.los.animals.AnimalFactory;
import cz.los.animals.AnimalProperties;
import cz.los.animals.goat.GoatProperties;

public class SandBox {

    public static void main(String[] args) {
        AnimalFactory factory = new AnimalFactory();
        AnimalProperties properties = new GoatProperties(1,1,1.0,1.0,1,1,1);
        Animal animal = factory.createAnimal(properties);
    }

}
