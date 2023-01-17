package cz.los.animals;

import cz.los.island.IslandCell;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
public abstract class Predator extends Animal {

    public Predator(AnimalProperties properties) {
        super(properties);
    }

    @Override
    protected void getFood() {
        log.info(ANIMAL_INFO + "started getting food...", getAnimalType(), getId());
        IslandCell position = this.getPosition();
        List<Animal> animals = position.getAnimals().values().stream()
                .flatMap(Collection::stream)
                .filter(animal -> animal instanceof Herbivorous)
                .collect(Collectors.toList());
        if (animals.size() <= 1) {
            return;
        }
        eat(animals.get(new Random().nextInt(animals.size() - 1)));
        log.info(ANIMAL_INFO + "ate food", getAnimalType(), getId());
    }
}
