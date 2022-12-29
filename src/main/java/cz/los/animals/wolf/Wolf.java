package cz.los.animals.wolf;

import cz.los.animals.Animal;
import cz.los.animals.AnimalType;

public class Wolf extends Animal {

    public Wolf(WolfProperties properties) {
        super(properties);
    }

    @Override
    protected AnimalType getAnimalName() {
        return AnimalType.WOLF;
    }

    @Override
    public double getEatableMass() {
        return getProperties().getWeight() * 0.2;
    }
}
