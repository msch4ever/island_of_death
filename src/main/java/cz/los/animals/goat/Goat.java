package cz.los.animals.goat;

import cz.los.animals.Animal;
import cz.los.animals.AnimalType;

public class Goat extends Animal {

    public Goat(GoatProperties properties) {
        super(properties);
    }

    @Override
    protected AnimalType getAnimalName() {
        return AnimalType.GOAT;
    }

    @Override
    public double getEatableMass() {
        return getProperties().getWeight() * 0.4;
    }
}
