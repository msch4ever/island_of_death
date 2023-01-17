package cz.los.island;

import cz.los.animals.Animal;
import cz.los.animals.AnimalType;
import cz.los.config.AnimalsConfig;
import cz.los.config.SimulationConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Getter
public class IslandCell implements Runnable {

    private final int x;
    private final int y;

    private final Map<AnimalType, Set<Animal>> animals;

    private final Vegetation vegetation;

    public IslandCell(int x, int y) {
        this.x = x;
        this.y = y;
        this.animals = Collections.synchronizedMap(new HashMap<>());
        this.vegetation =
                new Vegetation(new Random().nextInt(SimulationConfig.getInstance().getMaxVegetationLevel()));
    }

    public synchronized boolean addToAnimals(Animal animal) {
        AnimalType animalType = animal.getAnimalType();
        int currentPopulation = animals.computeIfAbsent(animalType, (k) -> new HashSet<>()).size();
        int maxAmountInCell = AnimalsConfig.getInstance().getAnimalProperties().get(animalType).getMaxAmountInCell();
        if (currentPopulation > maxAmountInCell) {
            log.warn("Cell {} is overpopulated with {}", this, animalType);
            return false;
        }
        return animals.get(animalType).add(animal);
    }

    public synchronized boolean removeFromAnimals(Animal animal) {
        return animals.get(animal.getAnimalType()).remove(animal);
    }

    @Override
    public void run() {
        log.info(lightToString() + "starts vegetation cycle");
        synchronized (vegetation) {
            vegetation.grow();
        }
        log.info(lightToString() + "finished vegetation cycle");
    }

    public String lightToString() {
        return "Cell X: " + x + " Y:" + y;
    }

    @Override
    public String toString() {
        return lightToString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IslandCell that = (IslandCell) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
