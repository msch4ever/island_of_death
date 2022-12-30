package cz.los.config;

import cz.los.animals.AnimalProperties;
import cz.los.animals.AnimalType;
import cz.los.animals.goat.GoatProperties;
import cz.los.animals.wolf.WolfProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import static cz.los.animals.AnimalType.GOAT;
import static cz.los.animals.AnimalType.WOLF;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AnimalsConfig {

    private Map<AnimalType, AnimalProperties> animalProperties;

    public static AnimalsConfig getInstance() {
        return AnimalsConfig.Keeper.instance;
    }

    private static class Keeper {
        private static final String ANIMAL_SPEC_FILE = "animal_spec.csv";
        private static final AnimalsConfig instance;

        static {
            Map<AnimalType, AnimalProperties> animalProperties = new HashMap<>();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            AnimalsConfig.Keeper.class.getClassLoader().getResourceAsStream(ANIMAL_SPEC_FILE)))) {
                reader.readLine();
                while (reader.ready()) {
                    String[] line = reader.readLine().replace(" ", "").split(",");
                    switch (line[0]) {
                        case ("goat") ->
                                animalProperties.put(GOAT, createAnimalProps(AnimalProperties.getRootConstructor(GoatProperties.class), line));
                        case ("wolf") ->
                                animalProperties.put(WOLF, createAnimalProps(AnimalProperties.getRootConstructor(WolfProperties.class), line));
                        //default -> throw new RuntimeException("Unrecognised animal type:" + line[0]); ToDo
                        default -> System.out.println("Unrecognised animal type: " + line[0]);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Could not parse animal specs file.");
            }
            instance = new AnimalsConfig(animalProperties);
        }

        private static AnimalProperties createAnimalProps(Constructor<? extends AnimalProperties> constructor, String[] line) {
            try {
                return constructor
                        .newInstance(Double.parseDouble(line[1]), Integer.parseInt(line[2]), Double.parseDouble(line[3]),
                                Double.parseDouble(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6]),
                                Integer.parseInt(line[7]), Integer.parseInt(line[8]), Integer.parseInt(line[9]));
            } catch (Exception e) {
                throw new RuntimeException("Could not map values on properties");
            }
        }


    }
}
