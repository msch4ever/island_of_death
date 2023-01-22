package cz.los.animals;

import lombok.Getter;

import java.lang.reflect.Constructor;

@Getter
public class AnimalProperties {

    private final AnimalType animalType;
    private final double weight;
    private final int range;
    private final double stomachCapacity;
    private final double dailyEnergyConsumption;
    private final int pregnancyDuration;
    private final int deathFromStarvingAfter;
    private final int maxLitterSize;
    private final int maxAmountInCell;
    private final int initialQuantity;

    public AnimalProperties(AnimalType animalType, double weight, int range, double stomachCapacity,
                             double dailyEnergyConsumption, int pregnancyDuration, int deathFromStarvingAfter,
                             int maxLitterSize, int maxAmountInCell, int initialQuantity) {
        this.animalType = animalType;
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
}
