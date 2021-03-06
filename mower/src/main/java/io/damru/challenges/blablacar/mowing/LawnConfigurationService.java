package io.damru.challenges.blablacar.mowing;

import io.damru.challenges.blablacar.mowing.model.Action;
import io.damru.challenges.blablacar.mowing.model.Lawn;
import io.damru.challenges.blablacar.mowing.model.Mower;
import io.damru.challenges.blablacar.mowing.model.Orientation;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Service
public class LawnConfigurationService {

    public static final String EMPTY_SPACE = " ";

    public Lawn load(InputStream inputStream) throws IOException {
        Lawn lawn = new Lawn();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        int lineIndex = 0;
        while (reader.ready()) {
            String line = reader.readLine();
            String[] values = line.split(EMPTY_SPACE);
            if (lineIndex == 0) {
                lawn = lawnConfiguration(values);
            } else {
                Mower mower = mowerConfiguration(lawn, values, lineIndex);
                ArrayList<Action> course = courseConfiguration(reader.readLine().toCharArray(), ++lineIndex);
                lawn.addMowerCourse(mower, course);
            }
            lineIndex++;
        }
        return lawn;
    }

    private ArrayList<Action> courseConfiguration(char[] courseConfig, int lineIndex) {
        ArrayList<Action> course = new ArrayList<>();
        for (int i = 0; i < courseConfig.length; i++) {
            try {
                course.add(Action.fromLabel(String.valueOf(courseConfig[i])));
            } catch (IllegalArgumentException e) {
                throw new ValidationException(
                        "Mower course configuration is incorrect (line " + lineIndex + ", col " + i + ").", e);
            }
        }

        return course;
    }

    private Mower mowerConfiguration(Lawn lawn, String[] mowerValues, int lineIndex) {
        Mower mower = new Mower();
        try {
            mower.setX(Integer.parseInt(mowerValues[0]));
            mower.setY(Integer.parseInt(mowerValues[1]));

            if (mower.getX() < lawn.getXMin() || mower.getX() > lawn.getXMax() || mower.getY() < lawn.getYMin() ||
                mower.getY() > lawn.getYMax()) {
                throw new ValidationException("Mower configuration is incorrect (line " + lineIndex +
                                              "): mower cannot start outside of lawn");
            }

            mower.setOrientation(Orientation.fromLabel(mowerValues[2]));
        } catch (Exception e) {
            throw new ValidationException("Mower configuration is incorrect (line " + lineIndex + ").", e);
        }
        return mower;
    }

    private Lawn lawnConfiguration(String[] coordinates) {
        Lawn lawn = new Lawn();
        if (coordinates.length < 2) {
            throw new ValidationException("Lawn configuration is incorrect (first line of file).");
        }
        try {
            lawn.setXMax(Integer.parseInt(coordinates[0]));
            lawn.setYMax(Integer.parseInt(coordinates[1]));

            if (lawn.getXMax() <= lawn.getXMin() || lawn.getYMax() <= lawn.getYMin()) {
                throw new ValidationException(
                        "Lawn configuration is incorrect (first line of file): perimeter cannot be null or negative");
            }
        } catch (NumberFormatException e) {
            throw new ValidationException("Lawn configuration is incorrect (first line of file).", e);
        }
        return lawn;
    }

}
