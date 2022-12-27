package cz.los;

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
        System.out.println("lol");
    }

}
