package io.damru.challenges.blablacar.mower.model;

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
        return BY_LABEL.get(label);
    }
}
