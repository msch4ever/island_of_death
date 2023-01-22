package cz.los.config;

import cz.los.animals.AnimalProperties;
import cz.los.animals.AnimalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
                        case ("goat"):
                            animalProperties.put(GOAT, createAnimalProps(GOAT, line));
                        case ("wolf"):
                            animalProperties.put(WOLF, createAnimalProps(WOLF, line));
                        default:
                            System.out.println("Unrecognised animal type: " + line[0]);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Could not parse animal specs file.");
            }
            instance = new AnimalsConfig(animalProperties);
        }

        private static AnimalProperties createAnimalProps(AnimalType type, String[] line) {
            return new AnimalProperties(type, Double.parseDouble(line[1]), Integer.parseInt(line[2]), Double.parseDouble(line[3]),
                    Double.parseDouble(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6]),
                    Integer.parseInt(line[7]), Integer.parseInt(line[8]), Integer.parseInt(line[9]));
        }
    }
}
