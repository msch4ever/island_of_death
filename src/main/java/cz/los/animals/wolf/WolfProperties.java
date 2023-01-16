package cz.los.animals.wolf;

import cz.los.animals.Animal;
import cz.los.animals.AnimalProperties;
import cz.los.animals.AnimalType;

public class WolfProperties extends AnimalProperties {

    public WolfProperties(double weight, int range, double stomachCapacity, double dailyEnergyConsumption,
                          int pregnancyDuration, int deathFromStarvingAfter, int maxLitterSize, int maxAmountInCell,
                          int initialQuantity) {
        super(weight, range, stomachCapacity, dailyEnergyConsumption, pregnancyDuration, deathFromStarvingAfter,
                maxLitterSize, maxAmountInCell, initialQuantity);
    }

    @Override
    public Animal createAnimal() {
        return new Wolf(this);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.WOLF;
    }
}
