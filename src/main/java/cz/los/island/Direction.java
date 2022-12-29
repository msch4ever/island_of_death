package cz.los.island;

import java.util.Random;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public static Direction prickRandomDirection() {
        return values()[new Random().nextInt(0, values().length)];
    }
}
