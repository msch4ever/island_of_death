package cz.los.config;

public final class SimulationConfigStatic {

    private SimulationConfigStatic() {
        throw new RuntimeException("Config class should not be instantiated!");
    }

    public static final int xDimension = 5;
    public static final int yDimension = 5;
    public static final int simulationLength = 10;
    public static final int dayLengthInSecs = 1;
    public static final int maxVegetationLevel = 200;
    public static final double growFactor = 1.5;

}
