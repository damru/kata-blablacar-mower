package io.damru.challenges.blablacar.mowning.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
public class LawnConfiguration {

    private Lawn lawn;
    private HashMap<Mower, ArrayList<Action>> mowersCourses;

    public void addMowerCourse(Mower mower, ArrayList<Action> course) {
        if (this.mowersCourses == null) {
            this.mowersCourses = new HashMap<>();
        }
        this.mowersCourses.put(mower, course);
    }
}
