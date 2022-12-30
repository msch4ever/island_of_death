package cz.los.island;

import cz.los.animals.Animal;
import cz.los.config.SimulationConfig;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Getter
public class IslandCell {

    private final int x;
    private final int y;

    private final List<Animal> animals;
    private final Vegetation vegetation;

    public IslandCell(int x, int y) {
        this.x = x;
        this.y = y;
        this.animals = Collections.synchronizedList(new ArrayList<>());
        this.vegetation =
                new Vegetation(new Random().nextInt(SimulationConfig.getInstance().getMaxVegetationLevel()));
    }

    public String lightToString() {
        return "Cell X: " + x + " Y:" + y;
    }
}
