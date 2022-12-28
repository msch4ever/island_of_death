package cz.los.animals.wolf;

import cz.los.animals.Animal;
import cz.los.animals.AnimalProperties;

public class WolfProperties extends AnimalProperties {

    public WolfProperties(int weight, int range, double stomachCapacity, double dailyEnergyConsumption,
                          int pregnancyDuration, int deathFromStarvingAfter, int maxLitterSize) {
        super(weight, range, stomachCapacity, dailyEnergyConsumption, pregnancyDuration, deathFromStarvingAfter,
                maxLitterSize);
    }

    @Override
    public Animal createAnimal() {
        return new Wolf(this);
    }
}
