package cz.los.animals;

import cz.los.config.AnimalsConfig;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalFactoryTest {

    private AnimalFactory factory;

    @BeforeEach
    public void setup() {
        System.out.println("!!!init method!!!");
        this.factory = new AnimalFactory();
    }

    @AfterEach
    public void cleanup() {
        System.out.println("!!!cleanup method!!!");
        this.factory = null;
    }

    @BeforeAll
    public static void overallSetup() {
        System.out.println("!!!BEFORE ALLL!!!");
        AnimalsConfig.getInstance();
    }

    @AfterAll
    public static void overallCleanup() {
        System.out.println("!!!AFTER ALLL!!!");
    }

    @EnumSource
    @ParameterizedTest(name = "Should create animal with {0} animal type provided")
    public void happyTest(AnimalType expected) {
        //when
        Animal result = factory.createAnimal(expected);

        //then
        assertNotNull(result);
        AnimalType actualAnimalType = result.getAnimalType();
        assertEquals(expected, actualAnimalType);
    }

    @NullSource
    @ParameterizedTest(name = "Should throw an exception if provided type is null")
    public void nullTest(AnimalType expected) {
        //when
        assertThrows(NullPointerException.class, () -> factory.createAnimal(expected));
    }

    @Test
    @Disabled("Disabled because I am lazy")
    @DisplayName("Should fail")
    public void badTest() {
        assert false;
    }
}
