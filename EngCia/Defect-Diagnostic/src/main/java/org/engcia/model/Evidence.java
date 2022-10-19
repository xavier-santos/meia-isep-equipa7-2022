package org.engcia.model;

public class Evidence extends Fact{
    public static final String AI_VISION = "Is the Computer vision system on?";
    public static final String MOLD_PARAMS = "Is the Mold parametrization correct?";
    private String evidence;
    private String value;

    public Evidence(String ev, String v) {
        evidence = ev;
        value = v;
    }

    public String getEvidence() {
        return evidence;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return (evidence + " = " + value);
    }

}

