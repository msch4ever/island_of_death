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
        }
        move();
        //eat();
        //reproduce();
        consumeEnergy();
    }

    public void setPosition(IslandCell islandCell) {
        if (!changeCell(islandCell)) {
            String error = String.format(ANIMAL_INFO.replace("{}", "%s") + ". Position setting is impossible.", getAnimalName(), id);
            log.error(error);
            throw new RuntimeException(error);
        }
    }

    private void move() {
        log.info(ANIMAL_INFO + " starts moving.", getAnimalName(), id);
        Random decider = new Random();
        for (int move = 0; move < properties.getRange(); move++) {
            if (decider.nextBoolean()) {
                IslandCell previous = position;
                moveToNextCell();
                if (previous != position) {
                    log.info(ANIMAL_INFO + " moved from {} to {}",
                            getAnimalName(), id, previous.lightToString(), position.lightToString());
                }
            }
        }
        log.info(ANIMAL_INFO + "finished moving.", getAnimalName(), id);
    }

    private void moveToNextCell() {
        int trysCount = 0;
        do {
            switch (Direction.prickRandomDirection()) {
                case UP : {
                    int nextY = position.getY() - 1;
                    if (isPossibleToGoThereVertically(nextY)) {
                        IslandCell potentialDestination = Island.getInstance().getCell(position.getY(), nextY);
                        if (changeCell(potentialDestination)) {
                            break;
                        }
                        trysCount++;
                    }
                    break;
                }
                case DOWN : {
                    int nextY = position.getY() + 1;
                    if (isPossibleToGoThereVertically(nextY)) {
                        IslandCell potentialDestination = Island.getInstance().getCell(position.getY(), nextY);
                        if (changeCell(potentialDestination)) {
                            break;
                        }
                        trysCount++;
                    }
                    break;
                }
                case LEFT : {
                    int nextX = position.getX() - 1;
                    if (isPossibleToGoThereHorizontally(nextX)) {
                        IslandCell potentialDestination = Island.getInstance().getCell(nextX, position.getY());
                        if (changeCell(potentialDestination)) {
                            break;
                        }
                        trysCount++;
                    }
                    break;
                }
                case RIGHT : {
                    int nextX = position.getY() + 1;
                    if (isPossibleToGoThereHorizontally(nextX)) {
                        IslandCell potentialDestination = Island.getInstance().getCell(nextX, position.getY());
                        if (changeCell(potentialDestination)) {
                            break;
                        }
                        trysCount++;
                    }
                    break;
                }
            }
        } while (trysCount < 5);
    }

    private boolean changeCell(IslandCell potentialDestination) {
        try {
            boolean cellIsFull = checkIfCellIsFull(potentialDestination);
            if (!cellIsFull) {
                this.position = potentialDestination;
                return true;
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            log.warn(ANIMAL_INFO + " an error occurred while making a move", getAnimalName(), id);
        }
        return false;
    }

    private boolean isPossibleToGoThereVertically(int y) {
        return y != Island.getInstance().getHeight() && y != -1;
    }

    private boolean isPossibleToGoThereHorizontally(int x) {
        return x != Island.getInstance().getWidth() && x != -1;
    }

    private boolean checkIfCellIsFull(IslandCell potentialDestination) {
        long currentAmountOfPopulation =
                potentialDestination.getAnimals().stream().filter(it -> it.getAnimalName() == getAnimalName()).count();
        return currentAmountOfPopulation > properties.getMaxAmountInCell();
    }

    private void consumeEnergy() {
        currentStomachLevel = currentStomachLevel - properties.getDailyEnergyConsumption();
        if (currentStomachLevel <= 0) {
            log.info(ANIMAL_INFO + "started starving.", id, getAnimalName());
            currentStomachLevel = 0;
            daysWithoutFood++;
        }

        if (daysWithoutFood > properties.getDeathFromStarvingAfter()) {
            alive = false;
        }
    }

    private void eat(Eatable eatable) {
        double eatableMass = eatable.getEatableMass();
        currentStomachLevel = currentStomachLevel + eatableMass;
        if (currentStomachLevel == properties.getStomachCapacity()) {
            currentStomachLevel = properties.getStomachCapacity();
        }
        daysWithoutFood = -1;
    }

    protected abstract AnimalType getAnimalName();

}
