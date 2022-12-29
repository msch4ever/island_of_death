package cz.los.animals;

import lombok.Getter;

import java.lang.reflect.Constructor;

@Getter
public abstract class AnimalProperties {

    private final int weight;
    private final int range;
    private final int maxAmountInCell;
    private final double stomachCapacity;
    private final double dailyEnergyConsumption;
    private final int pregnancyDuration;
    private final int deathFromStarvingAfter;
    private final int maxLitterSize;

    public AnimalProperties(int weight, int range, int maxAmountInCell, double stomachCapacity, double dailyEnergyConsumption,
                            int pregnancyDuration, int deathFromStarvingAfter, int maxLitterSize) {
        this.weight = weight;
        this.range = range;
        this.maxAmountInCell = maxAmountInCell;
        this.stomachCapacity = stomachCapacity;
        this.dailyEnergyConsumption = dailyEnergyConsumption;
        this.pregnancyDuration = pregnancyDuration;
        this.deathFromStarvingAfter = deathFromStarvingAfter;
        this.maxLitterSize = maxLitterSize;
    }

    public static Constructor<? extends AnimalProperties> getRootConstructor(Class<? extends AnimalProperties> properties) {
        try {
            return properties.getConstructor(int.class, int.class, int.class, double.class, double.class, int.class, int.class, int.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Could not find needed constructor");
        }
    }

    public abstract Animal createAnimal();

}
