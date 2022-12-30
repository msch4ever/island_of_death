package cz.los.island;

import cz.los.animals.Animal;
import cz.los.config.SimulationConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
@Getter
public class IslandCell implements Runnable {

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
}
