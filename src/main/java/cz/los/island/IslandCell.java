package cz.los.island;

import cz.los.animals.Animal;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class IslandCell {

    private int x;
    private int y;

    private final List<Animal> animals;

    public IslandCell(int x, int y) {
        this.x = x;
        this.y = y;
        this.animals = Collections.synchronizedList(new ArrayList<>());
    }

    public String lightToString() {
        return "Cell X: " + x + " Y:" + y;
    }
}
