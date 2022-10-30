package org.engcia.utils;

import org.engcia.model.Evidence;
import org.kie.api.runtime.ClassObjectFilter;

import java.util.Collection;

import static java.lang.Thread.sleep;

public class FiredRule{

    public static String question ="";
    public static boolean response;
    public static boolean answered;


    /*TODO:
        Make this code decent
    */
    public static boolean answer(String ev, String v) {
        answered=false;
        @SuppressWarnings("unchecked")
        Collection<Evidence> evidences = (Collection<Evidence>) Engine.KS.getObjects(new ClassObjectFilter(Evidence.class));
        boolean questionFound = false;
        Evidence evidence = null;
        for (Evidence e: evidences) {
            if (e.getEvidence().compareTo(ev) == 0) {
                questionFound = true;
                evidence = e;
                break;
            }
        }
        if (questionFound) {
            if (evidence.getValue().compareTo(v) == 0) {
                Engine.agendaEventListener.addLhs(evidence);
                return true;
            } else {
                // Clear LHS conditions set if a condition is false (conjunctive rules)
                Engine.agendaEventListener.resetLhs();
                return false;
            }
        }
        question = ev + "? ";

        while(!answered){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        answered = false;

        String answer = response?"yes": "no";

        Evidence e = new Evidence(ev, answer);
        Engine.KS.insert(e);

        if (answer.compareTo(v) == 0) {
            Engine.agendaEventListener.addLhs(e);
            return true;
        } else {
            // Clear LHS conditions set if a condition is false (conjunctive rules)
            Engine.agendaEventListener.resetLhs();
            return false;
        }
    }

}
