package cz.los.animals;

import cz.los.animals.goat.Goat;
import cz.los.animals.wolf.Wolf;
import cz.los.config.AnimalsConfig;

import java.util.Map;

public class AnimalFactory {

    public Animal createAnimal(AnimalType type) {
        Map<AnimalType, AnimalProperties> propertiesByType =
                AnimalsConfig.getInstance().getAnimalProperties();
        switch (type) {
            case WOLF: return new Wolf(propertiesByType.get(type));
            case GOAT: return new Goat(propertiesByType.get(type));
            default: throw new IllegalArgumentException("Not recognized animal type. " + type);
        }
    }
}
