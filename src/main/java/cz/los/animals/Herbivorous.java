package cz.los.animals;

import cz.los.island.IslandCell;
import cz.los.island.Vegetation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Herbivorous extends Animal {

    public Herbivorous(AnimalProperties properties) {
        super(properties);
    }

    @Override
    protected void getFood() {
        log.info(ANIMAL_INFO + "started getting food...", getAnimalType(), getId());
        IslandCell position = this.getPosition();
        Vegetation vegetation = position.getVegetation();
        eat(vegetation);
        log.info(ANIMAL_INFO + "ate food", getAnimalType(), getId());
    }
}
