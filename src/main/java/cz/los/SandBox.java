package cz.los;

import cz.los.animals.AnimalFactory;
import cz.los.animals.AnimalProperties;
import cz.los.animals.AnimalType;
import cz.los.config.AnimalsConfig;
import cz.los.island.Island;

public class SandBox {

    public static void main(String[] args) {
        AnimalFactory factory = new AnimalFactory();
        AnimalProperties goatProps = AnimalsConfig.getInstance().getAnimalProperties().get(AnimalType.GOAT);
        Island island = Island.getInstance();

        System.out.println();
    }

}
