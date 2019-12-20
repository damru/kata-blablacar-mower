package io.damru.challenges.blablacar.mower.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;

@Getter
@Setter
public class LawnConfiguration {

    private Lawn lawn;
    private HashMap<Mower, LinkedList<Action>> mowersCourses;

    public void addMowerCourse(Mower mower, LinkedList<Action> course) {
        if (this.mowersCourses == null) {
            this.mowersCourses = new HashMap<>();
        }
        this.mowersCourses.put(mower, course);
    }
}
