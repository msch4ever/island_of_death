package cz.los;

import cz.los.animals.AnimalFactory;
import cz.los.animals.AnimalProperties;
import cz.los.animals.AnimalType;
import cz.los.animals.Eatable;
import cz.los.animals.goat.Goat;
import cz.los.config.AnimalsConfig;
import cz.los.island.Island;

import static cz.los.AppRunner.populateIsland;

public class SandBox {

    public static void main(String[] args) {
        AnimalFactory factory = new AnimalFactory();
        AnimalProperties goatProps = AnimalsConfig.getInstance().getAnimalProperties().get(AnimalType.GOAT);
        Island island = Island.getInstance();

        populateIsland();

        System.out.println();
    }

}
