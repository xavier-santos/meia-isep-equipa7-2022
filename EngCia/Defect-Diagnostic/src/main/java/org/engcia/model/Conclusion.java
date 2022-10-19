package org.engcia.model;

import org.engcia.diagnostic.DefectDiagnostic;

public class Conclusion extends Fact{
    public static final String TURN_ON_AI_SYSTEM = "Turn on Computer vision system.";
    public static final String REVIEW_PARAMS = "Review Computer vision system parametrization";
    public static final String CHECK_AI_SYSTEM_PLC_COMMUNICATION = "Check the communication between the Computer vision system and the PLC.";
    public static final String UNKNOWN = "SENTA E CHORA.";

    private String description;

    public Conclusion(String description) {
        this.description = description;
        DefectDiagnostic.agendaEventListener.addRhs(this);
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return ("Conclusion: " + description);
    }

}
