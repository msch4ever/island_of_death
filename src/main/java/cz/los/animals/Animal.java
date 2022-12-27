package cz.los.animals;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public abstract class Animal {
    private final int weight;
    private final int range;
    private final double stomachCapacity;
    private final int pregnancyDuration;
    private final int deathFromStarvingAfter;
    private final int maxLitterSize;

    private int currentStomachLevel = (int) (stomachCapacity / 2);


}
