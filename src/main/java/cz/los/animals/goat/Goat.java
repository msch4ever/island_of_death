package cz.los.animals.goat;

import cz.los.animals.AnimalType;
import cz.los.animals.Herbivorous;

public class Goat extends Herbivorous {

    public Goat(GoatProperties properties) {
        super(properties);
    }

    @Override
    public AnimalType getAnimalName() {
        return AnimalType.GOAT;
    }

    @Override
    public double getEatableMass() {
        return getProperties().getWeight() * 0.4;
    }
}
