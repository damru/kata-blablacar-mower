package io.damru.challenges.blablacar.mowing.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum Action {
    LEFT("L"),
    RIGHT("R"),
    FORWARD("F");

    @Getter
    private String label;

    Action(String label) {
        this.label = label;
    }

    private static final Map<String, Action> BY_LABEL = new HashMap<>();

    static {
        for (Action a : values()) {
            BY_LABEL.put(a.label, a);
        }
    }

    public static Action fromLabel(String label) {
        Action a = BY_LABEL.get(label);
        if (a == null) {
            throw new IllegalArgumentException("Action " + label + " does not exist");
        }
        return a;
    }
}
