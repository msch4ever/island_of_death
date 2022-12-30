package cz.los.animals.goat;

import cz.los.animals.Animal;
import cz.los.animals.AnimalProperties;

public class GoatProperties extends AnimalProperties {

    public GoatProperties(double weight, int range, double stomachCapacity, double dailyEnergyConsumption,
                          int pregnancyDuration, int deathFromStarvingAfter, int maxLitterSize, int maxAmountInCell,
                          int initialQuantity) {
        super(weight, range, stomachCapacity, dailyEnergyConsumption, pregnancyDuration, deathFromStarvingAfter,
                maxLitterSize, maxAmountInCell, initialQuantity);
    }

    @Override
    public Animal createAnimal() {
        return new Goat(this);
    }
}
