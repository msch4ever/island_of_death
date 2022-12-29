package cz.los.animals.goat;

import cz.los.animals.Animal;
import cz.los.animals.AnimalProperties;

public class GoatProperties extends AnimalProperties {

    public GoatProperties(int weight, int range, int maxAmountInCell, double stomachCapacity,
                          double dailyEnergyConsumption, int pregnancyDuration, int deathFromStarvingAfter,
                          int maxLitterSize) {
        super(weight, range, maxAmountInCell, stomachCapacity, dailyEnergyConsumption, pregnancyDuration,
                deathFromStarvingAfter, maxLitterSize);
    }

    @Override
    public Animal createAnimal() {
        return new Goat(this);
    }
}
