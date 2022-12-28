package cz.los.animals;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum AnimalType {
    WOLF("wolf"), GOAT("goat");

    private static final Map<String, AnimalType> animalTypesByValue;

    private final String value;

    AnimalType(String value) {
        this.value = value;
    }

    static {
        animalTypesByValue =
                Collections.unmodifiableMap(
                        Arrays.stream(AnimalType.values())
                                .collect(Collectors.toMap(type -> type.value, Function.identity())));
    }
}
