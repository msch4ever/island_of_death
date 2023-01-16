package cz.los.animals;

import java.util.List;

public abstract class Predator extends Animal {

    public Predator(AnimalProperties properties) {
        super(properties);
    }

    @Override
    protected void getFood() {
    }
}
