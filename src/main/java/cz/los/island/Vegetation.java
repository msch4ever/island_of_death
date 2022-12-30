package cz.los.island;

import cz.los.animals.Eatable;
import cz.los.config.SimulationConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

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

    static {
        MAX_VEGETATION_LEVEL = SimulationConfig.getInstance().getMaxVegetationLevel();
        GROW_FACTOR = SimulationConfig.getInstance().getGrowFactor();
    }
}
