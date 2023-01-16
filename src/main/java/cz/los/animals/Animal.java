package cz.los.animals;


import cz.los.island.Direction;
import cz.los.island.Island;
import cz.los.island.IslandCell;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.UUID;

@Slf4j
public abstract class Animal implements Eatable, Runnable {

    private static final String ANIMAL_INFO = "{} id:{} ";

    @Getter(AccessLevel.PROTECTED)
    private final AnimalProperties properties;

    @Getter
    private final UUID id;
    @Getter
    private boolean alive;
    private int daysWithoutFood;
    private double currentStomachLevel;

    private IslandCell position;

    public Animal(AnimalProperties properties) {
        this.id = UUID.randomUUID();
        this.properties = properties;
        this.currentStomachLevel = properties.getStomachCapacity() / 2;
        this.daysWithoutFood = -1;
        this.alive = true;
    }

    @Override
    public void run() {
        log.info(ANIMAL_INFO + "starts daily routine.", getAnimalName(), id);
        if (!alive) {
            log.warn(ANIMAL_INFO + "is dead.", getAnimalName(), id);
            return;
        }
        move();
        getFood();
        //reproduce();
        consumeEnergy();
    }

    public abstract double getEatableMass();

    private void move() {
        log.info(ANIMAL_INFO + " starts moving.", getAnimalName(), id);
        Random decider = new Random();
        for (int move = 0; move < properties.getRange(); move++) {
            if (decider.nextBoolean()) {
                IslandCell previous = position;
                moveToNextCell();
                if (!previous.equals(this.position)) {
                    log.info(ANIMAL_INFO + " moved from {} to {}",
                            getAnimalName(), id, previous.lightToString(), position.lightToString());
                }
            }
        }
        log.info(ANIMAL_INFO + "finished moving.", getAnimalName(), id);
    }

    protected abstract void getFood();

    private void moveToNextCell() {
        int trysCount = 0;
        do {
            switch (Direction.prickRandomDirection()) {
                case UP: {
                    int nextY = position.getY() - 1;
                    if (isPossibleToGoThereVertically(nextY)) {
                        IslandCell potentialDestination = Island.getInstance().getCell(position.getY(), nextY);
                        if (changeCell(potentialDestination)) {
                            return;
                        }
                        trysCount++;
                    }
                }
                case DOWN: {
                    int nextY = position.getY() + 1;
                    if (isPossibleToGoThereVertically(nextY)) {
                        IslandCell potentialDestination = Island.getInstance().getCell(position.getY(), nextY);
                        if (changeCell(potentialDestination)) {
                            return;
                        }
                        trysCount++;
                    }
                }
                case LEFT: {
                    int nextX = position.getX() - 1;
                    if (isPossibleToGoThereHorizontally(nextX)) {
                        IslandCell potentialDestination = Island.getInstance().getCell(nextX, position.getY());
                        if (changeCell(potentialDestination)) {
                            return;
                        }
                        trysCount++;
                    }
                }
                case RIGHT: {
                    int nextX = position.getY() + 1;
                    if (isPossibleToGoThereHorizontally(nextX)) {
                        IslandCell potentialDestination = Island.getInstance().getCell(nextX, position.getY());
                        if (changeCell(potentialDestination)) {
                            return;
                        }
                        trysCount++;
                    }
                }
            }
        } while (trysCount < 5);
    }

    public synchronized boolean setCell(IslandCell potentialDestination) {
        try {
            if (!potentialDestination.addToAnimals(this)) {
                throw new RuntimeException("Could not add " + this + " to this cell.");
            }
            this.position = potentialDestination;
            return true;
        } catch (Exception e) {
            log.error(ANIMAL_INFO + " an error occurred while making a move", getAnimalName(), id);
            log.error(e.getMessage());
        }
        return false;
    }

    private synchronized boolean changeCell(IslandCell potentialDestination) {
        try {
            System.out.println(position.getAnimals().get(getAnimalName()).contains(this));
            if (this.position.removeFromAnimals(this)) {
                if (potentialDestination.addToAnimals(this)) {
                    this.position = potentialDestination;
                    return true;
                }
                return false;
            }
            return false;
        } catch (Exception e) {
            log.error(ANIMAL_INFO + " an error occurred while making a move", getAnimalName(), id);
            log.error(e.getMessage());
        }
        return false;
    }

    private boolean isPossibleToGoThereVertically(int y) {
        return y != Island.getInstance().getHeight() && y != -1;
    }

    private boolean isPossibleToGoThereHorizontally(int x) {
        return x != Island.getInstance().getWidth() && x != -1;
    }

    private void consumeEnergy() {
        currentStomachLevel = currentStomachLevel - properties.getDailyEnergyConsumption();
        if (currentStomachLevel <= 0) {
            currentStomachLevel = 0;
            daysWithoutFood++;
            if (daysWithoutFood == 0) {
                log.info(ANIMAL_INFO + "started starving.", getAnimalName(), id);
            } else {
                log.info(ANIMAL_INFO + "starving.", getAnimalName(), id);
            }
        }

        if (daysWithoutFood > properties.getDeathFromStarvingAfter()) {
            log.info(ANIMAL_INFO + "DIED from starving.", getAnimalName(), id);
            if (!position.removeFromAnimals(this)) {
             log.error("Could not even remove dead guy from cell...");
            }
            alive = false;
        }
    }

    protected void eat(Eatable eatable) {
        double consumed = eatable.consumeAsFood(properties.getStomachCapacity());
        currentStomachLevel = currentStomachLevel + consumed;
        if (currentStomachLevel >= properties.getStomachCapacity()) {
            currentStomachLevel = properties.getStomachCapacity();
        }
        daysWithoutFood = -1;
    }

    public abstract AnimalType getAnimalName();

    @Override
    public synchronized double consumeAsFood(double required) {
        double consumed;
        if (getEatableMass() >= required) {
            consumed = required;
        } else {
            consumed = getEatableMass();
        }
        this.alive = false;
        this.position.removeFromAnimals(this);
        return consumed;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "alive=" + alive +
                ", daysWithoutFood=" + daysWithoutFood +
                ", currentStomachLevel=" + currentStomachLevel +
                ", position=" + position +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;

        Animal animal = (Animal) o;

        return id.equals(animal.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
