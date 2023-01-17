package cz.los;

import cz.los.animals.Animal;
import cz.los.config.AnimalsConfig;
import cz.los.config.SimulationConfig;
import cz.los.island.Island;
import cz.los.island.IslandCell;
import cz.los.simulation.Engine;
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
        log.info("Island was created and populated.");
        runSimulation();
    }

    private static void initConfigs() {
        SimulationConfig.getInstance();
        AnimalsConfig.getInstance();
    }

    private static void createIsland() {
        Island.getInstance();
    }

    private static void populateIsland() {
        Island island = Island.getInstance();
        log.info("Populating island. {}", island);
        Random positionPicker = new Random();
        AnimalsConfig.getInstance().getAnimalProperties().values().forEach(animalProperties -> {
            log.info("Creating {} population...", animalProperties.getType());
            IslandCell position;
            for (int i = 0; i < animalProperties.getInitialQuantity(); i++) {
                Animal newAnimal = animalProperties.createAnimal();
                do {
                    int x = positionPicker.nextInt(island.getWidth());
                    int y = positionPicker.nextInt(island.getHeight());
                    position = island.getCell(x, y);
                } while (!newAnimal.setCell(position));
                island.getAnimals().add(newAnimal);
            }
        });
        log.info("Island populated. {}", island);
    }

    private static void runSimulation() {
        Engine engine = new Engine();
        engine.startSynchronous();
        //engine.start();
    }

}
