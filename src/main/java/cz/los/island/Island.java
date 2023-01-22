package cz.los.island;

import cz.los.animals.Animal;
import cz.los.config.SimulationConfigStatic;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.*;
import java.util.stream.Stream;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Island {

    IslandCell[][] island;
    Set<Animal> animals = Collections.synchronizedSet(new HashSet<>());

    public IslandCell getCell(int x, int y) {
        return island[y][x];
    }

    public int getWidth() {
        return island[0].length;
    }

    public int getHeight() {
        return island.length;
    }

    public static Island getInstance() {
        return Keeper.instance;
    }

    private static class Keeper {
        private static final Island instance;

        static {
            IslandCell[][] island =
                    new IslandCell[SimulationConfigStatic.xDimension][SimulationConfigStatic.yDimension];
            for (int y = 0; y < island[0].length; y++) {
                for (int x = 0; x < island.length; x++) {
                    island[y][x] = new IslandCell(x, y);
                }
            }
            instance = new Island(island);
        }
    }

    @Override
    public String toString() {
        return String.format("Island with dimensions x:%s y:%s \nCurrent population: %s\nCurrent vegetation: %s",
                island.length, island[0].length,
                animals.stream().filter(Animal::isAlive).count(),
                getVegetationLevelFunctional());
    }

    private double getVegetationLevelFunctional() {
        return Stream.of(island)
                .flatMap(Stream::of)
                .map(it -> it.getVegetation().getVegetationLevel())
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private double getVegetationLevel() {
        double totalVegetation = 0;
        for(IslandCell[] islandCells : island) {
            for (IslandCell islandCell : islandCells) {
                totalVegetation += islandCell.getVegetation().getVegetationLevel();
            }
        }
        return totalVegetation;
    }
}