package cz.los;

import cz.los.animals.Animal;
import cz.los.config.AnimalsConfig;
import cz.los.config.SimulationConfig;
import cz.los.island.Island;
import cz.los.island.IslandCell;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class AppRunner {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        initConfigs();
        createIsland();
        populateIsland();
        /*runSimulation();*/
        log.info("Island was created and populated.");
    }

    private static void initConfigs() {
        SimulationConfig config = SimulationConfig.getInstance();
        AnimalsConfig animalsConfig = AnimalsConfig.getInstance();
    }

    private static void createIsland() {
        Island island = Island.getInstance();
    }

    private static void populateIsland() {
        Island island = Island.getInstance();
        Random positionPicker = new Random();
        AnimalsConfig.getInstance().getAnimalProperties().values().forEach(animalProperties -> {
            for (int i = 0; i < animalProperties.getInitialQuantity(); i++) {
                Animal newAnimal = animalProperties.createAnimal();
                int x = positionPicker.nextInt(island.getWidth());
                int y = positionPicker.nextInt(island.getHeight());
                IslandCell position = island.getCell(x, y);
                newAnimal.setPosition(position);
                island.getAnimals().add(newAnimal);
                position.getAnimals().add(newAnimal);
            }
        });
    }

}
