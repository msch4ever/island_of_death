package cz.los;

import cz.los.animals.Animal;
import cz.los.animals.AnimalFactory;
import cz.los.animals.AnimalProperties;
import cz.los.animals.AnimalType;
import cz.los.config.AnimalsConfig;
import cz.los.config.SimulationConfig;
import cz.los.island.Island;
import cz.los.island.IslandCell;
import cz.los.simulation.Engine;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class AppRunner {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        initConfigs();
        createIsland();
        populateIsland();
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
        AnimalFactory factory = new AnimalFactory();
        Island island = Island.getInstance();
        log.info("Populating island. {}", island);
        Random positionPicker = new Random();
        Map<AnimalType,AnimalProperties> propsByType = AnimalsConfig.getInstance().getAnimalProperties();
        for (AnimalType type : propsByType.keySet()) {
            log.info("Creating {} population...", type);
            AnimalProperties properties = propsByType.get(type);
            IslandCell position;
            for (int i = 0; i < properties.getInitialQuantity(); i++) {
                Animal newAnimal = factory.createAnimal(type);
                do {
                    int x = positionPicker.nextInt(island.getWidth());
                    int y = positionPicker.nextInt(island.getHeight());
                    position = island.getCell(x, y);
                } while (!newAnimal.setCell(position));
                island.getAnimals().add(newAnimal);
            }
        }
        log.info("Island populated. {}", island);
        log.info("Island was created and populated.");
    }

    private static void runSimulation() {
        Engine engine = new Engine();
        engine.startSynchronous();
        //engine.start();
    }

}
