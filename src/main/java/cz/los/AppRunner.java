package cz.los;

import cz.los.config.AnimalsConfig;
import cz.los.config.SimulationConfig;

public class AppRunner {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        initConfigs();
        /*createIsland();
        populateIsland();
        runSimulation();*/
    }

    private static void initConfigs() {
        SimulationConfig config = SimulationConfig.getInstance();
        AnimalsConfig animalsConfig = AnimalsConfig.getInstance();
        System.out.println("lol");
    }

}
