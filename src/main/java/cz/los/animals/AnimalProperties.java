package cz.los.animals;

import lombok.Getter;

import java.lang.reflect.Constructor;

@Getter
public abstract class AnimalProperties {

    private final double weight;
    private final int range;
    private final double stomachCapacity;
    private final double dailyEnergyConsumption;
    private final int pregnancyDuration;
    private final int deathFromStarvingAfter;
    private final int maxLitterSize;
    private final int maxAmountInCell;
    private final int initialQuantity;

    public AnimalProperties(double weight, int range, double stomachCapacity, double dailyEnergyConsumption,
                            int pregnancyDuration, int deathFromStarvingAfter, int maxLitterSize, int maxAmountInCell,
                            int initialQuantity) {
        this.weight = weight;
        this.range = range;
        this.stomachCapacity = stomachCapacity;
        this.dailyEnergyConsumption = dailyEnergyConsumption;
        this.pregnancyDuration = pregnancyDuration;
        this.deathFromStarvingAfter = deathFromStarvingAfter;
        this.maxLitterSize = maxLitterSize;
        this.maxAmountInCell = maxAmountInCell;
        this.initialQuantity = initialQuantity;
    }

    public static Constructor<? extends AnimalProperties> getRootConstructor(Class<? extends AnimalProperties> properties) {
        try {
            return properties.getConstructor(double.class, int.class, double.class, double.class, int.class, int.class, int.class, int.class, int.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Could not find needed constructor");
        }
    }

    public abstract Animal createAnimal();

    public abstract AnimalType getType();

}
