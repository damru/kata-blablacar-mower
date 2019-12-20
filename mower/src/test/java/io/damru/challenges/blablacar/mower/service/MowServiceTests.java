package io.damru.challenges.blablacar.mower.service;

import io.damru.challenges.blablacar.mower.MowService;
import io.damru.challenges.blablacar.mower.model.Action;
import io.damru.challenges.blablacar.mower.model.Lawn;
import io.damru.challenges.blablacar.mower.model.Mower;
import io.damru.challenges.blablacar.mower.model.Orientation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowServiceTests {

    private static MowService mowService;
    private static Lawn lawn;
    private static HashMap<Mower, LinkedList<Action>> mowersCourses;

    @BeforeAll
    public static void init() {
        mowService = new MowService();
    }


    @Test
    public void should_return_mower_last_position_and_orientation() {
        // Given
        lawn = new Lawn();
        lawn.setXMax(5);
        lawn.setYMax(5);

        mowersCourses = new HashMap<>();
        Mower mowerConfig = new Mower();
        LinkedList<Action> actionsConfig = new LinkedList<>();
        mowerConfig.setOrientation(Orientation.NORTH);
        mowerConfig.setX(1);
        mowerConfig.setY(2);
        actionsConfig.push(Action.LEFT);
        actionsConfig.push(Action.FORWARD);
        actionsConfig.push(Action.LEFT);
        actionsConfig.push(Action.FORWARD);
        actionsConfig.push(Action.LEFT);
        actionsConfig.push(Action.FORWARD);
        actionsConfig.push(Action.LEFT);
        actionsConfig.push(Action.FORWARD);
        actionsConfig.push(Action.FORWARD);
        mowersCourses.put(mowerConfig, actionsConfig);

        // When
        Set<Mower> mowers = mowService.mow(lawn, mowersCourses);

        // Then
        assertEquals(1, mowers.size(), "Number of mowers");
        Mower mower = mowers.stream().findFirst().get();
        assertEquals(Orientation.NORTH, mower.getOrientation(), "Mower's orientation after course");
        assertEquals(1, mower.getX(), "Mower's last X position");
        assertEquals(3, mower.getY(), "Mower's last Y position");
    }

}
