package cz.los.animals.wolf;

import cz.los.animals.Animal;
import cz.los.animals.AnimalType;
import cz.los.animals.Predator;

public class Wolf extends Predator {

    public Wolf(WolfProperties properties) {
        super(properties);
    }

    @Override
    public AnimalType getAnimalType() {
        return AnimalType.WOLF;
    }

    @Override
    public double getEatableMass() {
        return getProperties().getWeight() * 0.2;
    }
}
