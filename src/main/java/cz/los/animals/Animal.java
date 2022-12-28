package cz.los.animals;


public abstract class Animal {

    private final AnimalProperties properties;

    private int currentStomachLevel;

    public Animal(AnimalProperties properties) {
        this.properties = properties;
        this.currentStomachLevel = (int) (properties.getStomachCapacity() / 2);
    }


}
