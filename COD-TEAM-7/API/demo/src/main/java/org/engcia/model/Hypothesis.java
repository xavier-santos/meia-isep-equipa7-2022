package org.engcia.model;

import org.engcia.DefectDiagnostic;

public class Hypothesis extends Fact{
    private String description;
    private String value;

    public Hypothesis(String description, String value) {
        this.description = description;
        this.value = value;
    }

    public final void init() {
        DefectDiagnostic.agendaEventListener.addRhs(this);
    }

    public static Hypothesis create(String description, String value) {
        Hypothesis a = new Hypothesis(description, value);
        a.init();
        return a;
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