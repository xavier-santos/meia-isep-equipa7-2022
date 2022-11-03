package org.engcia.model;

import org.engcia.DefectDiagnostic;

public class Conclusion extends Fact{

    //Lack of component
    public static final String TURN_ON_AI_SYSTEM = "Turn on Computer vision system";
    public static final String REVIEW_PARAMS = "Review Computer vision system parametrization";
    public static final String CHECK_AI_SYSTEM_PLC_COMMUNICATION = "Check communication between Computer vision system and PLC";

    //Lack of filling
    public static final String WRONG_MOLD_CLEANING = "Train workers on cleaning mold standards";

    //Misplaced component
    public static final String SUPPORT_BRACKETS_CORRECTION = "Review mold (Support brackets correction)";
    public static final String SUPPORT_BRACKETS_BAD_PLACEMENT = "Train workers on support brackets correct placement";

    //Bubbled foam part
    public static final String SEAL_TAPE_REPLACING = "Replace seal tape";
    public static final String SEAL_TAPE_POSITION_CORRECTION = "Correct seal tape position";
    public static final String MOLD_REVISION = "Review mold";

    //Collapsed foam part
    public static final String RESET_PARAMETERS = "Reset foam injection parameters according to standard";
    public static final String REVIEW_PRODUCT_DEBTS = "Rectify product debts";
    public static final String REPLACE_PRODUCTS = "Replace products";

    //Ripped foam part
    public static final String TRAJECTORY_PISTOL_TRAINING = "Train worker's pistol trajectory";
    public static final String CLEAN_PISTOL_NOZZLE = "Clean pistol nozzle";
    public static final String CLEAN_PISTOL_FILTER = "Clean pistol filter";
    public static final String ROBOT_TRAJECTORY_CORRECTION = "Correct robot trajectory";
    public static final String MANUAL_DEMOULDING_PROCESS_CORRECTION = "Train workers to perform correctly the manual demoulding process";


    //Common
    public static final String AUTOVENT_MAINTENANCE = "Review mold (Autovent maintenance)"; //filling, bubbling, collapsing
    public static final String TRAJECTORY_CORRECTION = "Correct injection trajectory"; //bubbling, collapsing
    public static final String SEAL_TAPE_PLACING = "Place aluminium seal tape on mold"; //bubbling, collapsing
    public static final String TEMPERATURE_ADJUSTMENT = "Adjust the temperature to be on the interval: 56±2ºC"; //collapsing, ripping

    //General
    public static final String UNKNOWN = "Please contact support system";

    private String description;

    public Conclusion(String description) {
        this.description = description;
    }

    public final void init() {
        DefectDiagnostic.agendaEventListener.addRhs(this);
    }

    public static Conclusion create(String description) {
        Conclusion a = new Conclusion(description);
        a.init();
        return a;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return ("Conclusion: " + description);
    }

}
