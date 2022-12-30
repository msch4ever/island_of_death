package cz.los.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.InputStream;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimulationConfig {

    private IslandDimensions islandDimensions;
    private int simulationLength;
    private int maxVegetationLevel;
    private double growFactor;

    public static SimulationConfig getInstance() {
        return Keeper.instance;
    }

    @Getter
    @NoArgsConstructor
    public static class IslandDimensions {
        int x;
        int y;
    }

    private static class Keeper {
        private static final String SIMULATION_CONFIG_FILE = "simulation_config.yaml";
        private static final SimulationConfig instance;

        static {
            try (InputStream is = Keeper.class.getClassLoader().getResourceAsStream(SIMULATION_CONFIG_FILE)) {
                ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
                instance = objectMapper.readValue(is, SimulationConfig.class);
            } catch (Exception e) {
                throw new RuntimeException("Could not parse simulation config file.");
            }
        }
    }

}
