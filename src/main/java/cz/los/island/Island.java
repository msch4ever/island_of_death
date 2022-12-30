package cz.los.island;

import cz.los.animals.Animal;
import cz.los.config.SimulationConfig;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Island {

    IslandCell[][] island;
    List<Animal> animals = Collections.synchronizedList(new ArrayList<>());

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
            SimulationConfig config = SimulationConfig.getInstance();
            IslandCell[][] island =
                    new IslandCell[config.getIslandDimensions().getX()][config.getIslandDimensions().getY()];
            for (int y = 0; y < island[0].length; y++) {
                for (int x = 0; x < island.length; x++) {
                    island[y][x] = new IslandCell(x, y);
                }
            }
            instance = new Island(island);
        }
    }
}