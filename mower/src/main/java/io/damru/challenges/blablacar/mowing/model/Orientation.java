package io.damru.challenges.blablacar.mowing.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum Orientation {
    NORTH("N"),
    EAST("E"),
    WEST("W"),
    SOUTH("S");


    @Getter
    private String label;

    Orientation(String label) {
        this.label = label;
    }

    private static final Map<String, Orientation> BY_LABEL = new HashMap<>();

    static {
        for (Orientation o : values()) {
            BY_LABEL.put(o.label, o);
        }
    }

    public static Orientation fromLabel(String label) {
        return BY_LABEL.get(label);
    }
}
