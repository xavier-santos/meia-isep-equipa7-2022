package org.engcia.model;

public class Evidence extends Fact{
    //Lack of component
    public static final String AI_VISION = "Is Computer vision system on";
    public static final String MOLD_PARAMS = "Is Mold parametrization correct";

    //Lack of filling

    //Misplaced component
    public static final String STANDARD_FIXING_BRACKETS = "Do standard fixing brackets follow the standard";

    //Bubbled foam part
    public static final String SEAL_TAPE = "Are there any seal tapes on mold";
    public static final String SEAL_TAPE_STATE = "Are the seal tapes in good state";
    public static final String SEAL_TAPE_POSITION = "Are the seal tapes correctly positioned";

    //Collapsed foam part
    public static final String FOAM_INJECTION_PARAMETERS = "Is foam injection parameters receipt according to standard";
    public static final String FOAMMAT_SUCCESS = "Was Foam Mat successfully performed";
    public static final String EXPECTED_PRODUCT_DEBTS = "Are product debts on the expected range";

    //Ripped foam part
    public static final String AUTO_PULVERIZATION = "Is pulverization automatic";
    public static final String STANDARD_MANUAL_PULVERIZATION = "Is the manual pistol fan according to standard";
    public static final String STANDARD_MANUAL_OPERATOR = "Is the worker following the trajectory according to guidelines";
    public static final String STANDARD_AUTO_PULVERIZATION = "Is the automatic pistol fan according to standard";
    public static final String HYDRAULIC_CIRCUIT_FLOW = "Is hydraulic circuit flow ok";
    public static final String ROBOT_TRAJECTORY = "Is robot trajectory ok";

    //Common evidences
    public static final String AUTOVENT_OPERATION = "Is degassing autovent operation ok"; //Lack of filling, bubbling, collapsing
    public static final String SEAL_ANOMALIES = "Is there any visible seal anomalies on mold"; //bubbling, collapsing
    public static final String INJECTION_TRAJECTORY = "Is the injection trajectory being done accordingly to the standard"; //bubbling, collapsing
    public static final String TEMPERATURE_CHECK = "Is mold temperature between 56±2ºC"; //collapsing, ripping


    private final String evidence;
    private final String value;

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

