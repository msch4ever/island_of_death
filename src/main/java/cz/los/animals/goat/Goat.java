package cz.los.animals.goat;

import cz.los.animals.AnimalType;
import cz.los.animals.Herbivorous;
import cz.los.animals.wolf.Wolf;


public class Goat extends Herbivorous {


    public Goat(GoatProperties properties) {
        super(properties);
    }

    @Override
    public AnimalType getAnimalType() {
        return AnimalType.GOAT;
    }

    @Override
    public double getEatableMass() {
        return getProperties().getWeight() * 0.4;
    }

    @Override
    public AnimalType getType() {
        return AnimalType.GOAT;
    }
}
