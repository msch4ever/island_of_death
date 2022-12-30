package cz.los.island;

import cz.los.animals.Eatable;
import cz.los.config.SimulationConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
@Getter
@AllArgsConstructor
public class Vegetation implements Eatable {

    private static final int MAX_VEGETATION_LEVEL;
    private static final double GROW_FACTOR;

    private int vegetation;

    @Override
    public double getEatableMass() {
        return vegetation;
    }

    public void grow() {
        if (vegetation < 1) {
            if (!touchOfGod()) {
                log.warn("Cannot grow vegetation as there is no life here");
                return;
            }
        }
        vegetation = (int) (vegetation * GROW_FACTOR);
    }

    private boolean touchOfGod() {
        Random god = new Random();
        if (god.nextInt(1000) < 11) {
            log.info("It was a touch of God - a true miracle. There is hope for this vegetation now.");
            vegetation = (int) (SimulationConfig.getInstance().getMaxVegetationLevel() * 0.1);
            return true;
        }
        return false;
    }

    static {
        MAX_VEGETATION_LEVEL = SimulationConfig.getInstance().getMaxVegetationLevel();
        GROW_FACTOR = SimulationConfig.getInstance().getGrowFactor();
    }
}
