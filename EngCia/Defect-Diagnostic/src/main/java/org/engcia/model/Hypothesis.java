package org.engcia.model;

import org.engcia.utils.Engine;

public class Hypothesis extends Fact{
    private final String description;
    private final String value;

    public Hypothesis(String description, String value) {
        this.description = description;
        this.value = value;
        Engine.agendaEventListener.addRhs(this);
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return (description + " = " + value);
    }
}