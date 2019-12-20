package io.damru.challenges.blablacar.mowing;

import io.damru.challenges.blablacar.mowing.model.Action;
import io.damru.challenges.blablacar.mowing.model.Lawn;
import io.damru.challenges.blablacar.mowing.model.Mower;
import io.damru.challenges.blablacar.mowing.model.Orientation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowingServiceTests {

    private static MowingService mowingService;
    private static Lawn lawn;
    private static HashMap<Mower, ArrayList<Action>> mowersCourses;

    @BeforeAll
    public static void init() {
        mowingService = new MowingService();
    }


    @Test
    public void should_return_mower_last_position_and_orientation() {
        // Given
        lawn = new Lawn();
        lawn.setXMax(5);
        lawn.setYMax(5);

        mowersCourses = new HashMap<>();
        Mower mowerConfig = new Mower();
        ArrayList<Action> actionsConfig = new ArrayList<>();
        mowerConfig.setOrientation(Orientation.NORTH);
        mowerConfig.setX(1);
        mowerConfig.setY(2);
        actionsConfig.add(Action.LEFT);
        actionsConfig.add(Action.FORWARD);
        actionsConfig.add(Action.LEFT);
        actionsConfig.add(Action.FORWARD);
        actionsConfig.add(Action.LEFT);
        actionsConfig.add(Action.FORWARD);
        actionsConfig.add(Action.LEFT);
        actionsConfig.add(Action.FORWARD);
        actionsConfig.add(Action.FORWARD);
        mowersCourses.put(mowerConfig, actionsConfig);

        // When
        Set<Mower> mowers = mowingService.mow(lawn, mowersCourses);

        // Then
        assertEquals(1, mowers.size(), "Number of mowers");
        Mower mower = mowers.stream().findFirst().get();
        assertEquals(Orientation.NORTH, mower.getOrientation(), "Mower's orientation after course");
        assertEquals(1, mower.getX(), "Mower's last X position");
        assertEquals(3, mower.getY(), "Mower's last Y position");
    }

    @Test
    public void should_return_mower_last_position_within_the_lawn_perimeter() {
        // Given
        lawn = new Lawn();
        lawn.setXMax(1);
        lawn.setYMax(1);

        mowersCourses = new HashMap<>();
        Mower mowerConfig = new Mower();
        ArrayList<Action> actionsConfig = new ArrayList<>();
        mowerConfig.setOrientation(Orientation.SOUTH);
        mowerConfig.setX(0);
        mowerConfig.setY(1);
        actionsConfig.add(Action.FORWARD);
        actionsConfig.add(Action.FORWARD);
        actionsConfig.add(Action.LEFT);
        actionsConfig.add(Action.FORWARD);
        actionsConfig.add(Action.FORWARD);
        mowersCourses.put(mowerConfig, actionsConfig);

        // When
        Set<Mower> mowers = mowingService.mow(lawn, mowersCourses);

        // Then
        Mower mower = mowers.stream().findFirst().get();
        assertEquals(1, mower.getX(), "Mower's last X position");
        assertEquals(0, mower.getY(), "Mower's last Y position");
    }
}
