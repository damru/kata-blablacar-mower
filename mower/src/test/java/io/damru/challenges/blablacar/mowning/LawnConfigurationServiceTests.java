package io.damru.challenges.blablacar.mowning;


import io.damru.challenges.blablacar.mowning.model.LawnConfiguration;
import io.damru.challenges.blablacar.mowning.model.Mower;
import io.damru.challenges.blablacar.mowning.model.Orientation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ValidationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LawnConfigurationServiceTests {

    @InjectMocks
    private static LawnConfigurationService lawnConfigurationServiceTests;

    @Test
    public void lawnConfiguration_should_be_loaded_from_file() throws IOException {
        // Given
        InputStream config = loadFile("src/test/resources/example1.txt");

        // When
        LawnConfiguration lawnConfiguration = lawnConfigurationServiceTests.load(config);

        // Then
        assertEquals(0, lawnConfiguration.getLawn().getXMin(), "Lawn min X");
        assertEquals(5, lawnConfiguration.getLawn().getXMax(), "Lawn max X");
        assertEquals(0, lawnConfiguration.getLawn().getYMin(), "Lawn min Y");
        assertEquals(5, lawnConfiguration.getLawn().getYMax(), "Lawn max Y");
        assertEquals(2, lawnConfiguration.getMowersCourses().size(), "Number of mowers to move");
        Mower firstMower = lawnConfiguration.getMowersCourses().keySet().stream()
                                            .filter(mower -> Orientation.NORTH.equals(mower.getOrientation()))
                                            .findFirst().get();
        assertEquals(Orientation.NORTH, firstMower.getOrientation(), "First mower's orientation");
        assertEquals(1, firstMower.getX(), "First mower's X position");
        assertEquals(2, firstMower.getY(), "First mower's Y position");
        assertEquals(9, lawnConfiguration.getMowersCourses().get(firstMower).size(), "First mower's number of actions");
    }

    @Test
    public void should_throw_exception_when_perimeter_format_is_invalid() throws IOException {
        // Given
        InputStream config = loadFile("src/test/resources/badFormat_lawn_perimeter.txt");

        // WhenThen
        assertThrows(ValidationException.class, () -> lawnConfigurationServiceTests.load(config));
    }

    @Test
    public void should_throw_exception_when_mower_position_format_is_invalid() throws IOException {
        // Given
        InputStream config = loadFile("src/test/resources/badFormat_mower_position.txt");

        // WhenThen
        assertThrows(ValidationException.class, () -> lawnConfigurationServiceTests.load(config));
    }

    @Test
    public void should_throw_exception_when_mower_course_is_invalid() throws IOException {
        // Given
        InputStream config = loadFile("src/test/resources/bad_mower_course.txt");

        // WhenThen
        assertThrows(ValidationException.class, () -> lawnConfigurationServiceTests.load(config));
    }

    @Test
    public void should_throw_exception_when_mower_position_is_invalid() throws IOException {
        // Given
        InputStream config = loadFile("src/test/resources/bad_mower_position.txt");

        // WhenThen
        assertThrows(ValidationException.class, () -> lawnConfigurationServiceTests.load(config));
    }

    @Test
    public void should_throw_exception_when_lawn_perimeter_is_invalid() throws FileNotFoundException {
        // Given
        InputStream config = loadFile("src/test/resources/bad_lawn_perimeter.txt");

        // WhenThen
        assertThrows(ValidationException.class, () -> lawnConfigurationServiceTests.load(config));
    }

    private InputStream loadFile(String path) throws FileNotFoundException {
        File initialFile = new File(path);
        return new FileInputStream(initialFile);
    }
}
