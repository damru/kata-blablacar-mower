package io.damru.challenges.blablacar.mowing;


import io.damru.challenges.blablacar.mowing.model.Lawn;
import io.damru.challenges.blablacar.mowing.model.Mower;
import io.damru.challenges.blablacar.mowing.model.Orientation;
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
    private static LawnConfigurationService lawnConfigurationService;

    @Test
    public void lawnConfiguration_should_be_loaded_from_file() throws IOException {
        // Given
        InputStream config = loadFile("src/test/resources/example1.txt");

        // When
        Lawn lawn = lawnConfigurationService.load(config);

        // Then
        assertEquals(0, lawn.getXMin(), "Lawn min X");
        assertEquals(5, lawn.getXMax(), "Lawn max X");
        assertEquals(0, lawn.getYMin(), "Lawn min Y");
        assertEquals(5, lawn.getYMax(), "Lawn max Y");
        assertEquals(2, lawn.getMowersCourses().size(), "Number of mowers to move");
        Mower firstMower = lawn.getMowersCourses().keySet().stream()
                               .filter(mower -> Orientation.NORTH.equals(mower.getOrientation()))
                               .findFirst().get();
        assertEquals(Orientation.NORTH, firstMower.getOrientation(), "First mower's orientation");
        assertEquals(1, firstMower.getX(), "First mower's X position");
        assertEquals(2, firstMower.getY(), "First mower's Y position");
        assertEquals(9, lawn.getMowersCourses().get(firstMower).size(), "First mower's number of actions");
    }

    @Test
    public void should_throw_exception_when_perimeter_format_is_invalid() throws IOException {
        // Given
        InputStream config = loadFile("src/test/resources/badFormat_lawn_perimeter.txt");

        // WhenThen
        assertThrows(ValidationException.class, () -> lawnConfigurationService.load(config));
    }

    @Test
    public void should_throw_exception_when_mower_position_format_is_invalid() throws IOException {
        // Given
        InputStream config = loadFile("src/test/resources/badFormat_mower_position.txt");

        // WhenThen
        assertThrows(ValidationException.class, () -> lawnConfigurationService.load(config));
    }

    @Test
    public void should_throw_exception_when_mower_course_is_invalid() throws IOException {
        // Given
        InputStream config = loadFile("src/test/resources/bad_mower_course.txt");

        // WhenThen
        assertThrows(ValidationException.class, () -> lawnConfigurationService.load(config));
    }

    @Test
    public void should_throw_exception_when_mower_position_is_invalid() throws IOException {
        // Given
        InputStream config = loadFile("src/test/resources/bad_mower_position.txt");

        // WhenThen
        assertThrows(ValidationException.class, () -> lawnConfigurationService.load(config));
    }

    @Test
    public void should_throw_exception_when_lawn_perimeter_is_invalid() throws FileNotFoundException {
        // Given
        InputStream config = loadFile("src/test/resources/bad_lawn_perimeter.txt");

        // WhenThen
        assertThrows(ValidationException.class, () -> lawnConfigurationService.load(config));
    }

    private InputStream loadFile(String path) throws FileNotFoundException {
        File initialFile = new File(path);
        return new FileInputStream(initialFile);
    }
}
