package org.engcia.view;

import org.engcia.diagnostic.DefectDiagnostic;
import org.engcia.model.Evidence;
import org.kie.api.runtime.ClassObjectFilter;

import java.util.Collection;

import static java.lang.Thread.sleep;

public class QuestionFactory {

    public static String question;
    public static boolean newQuestion=false;
    public static boolean answer;
    public static boolean answered=false;

    public static boolean answer(String ev, String v) throws InterruptedException {
        Collection<Evidence> evidences = (Collection<Evidence>) DefectDiagnostic.KS.getObjects(new ClassObjectFilter(Evidence.class));
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
                DefectDiagnostic.agendaEventListener.addLhs(evidence);
                return true;
            } else {
                // Clear LHS conditions set if a condition is false (conjunctive rules)
                DefectDiagnostic.agendaEventListener.resetLhs();
                return false;
            }
        }
        question = ev + "?";
        newQuestion = true;

        while(!answered){
           sleep(500);
        }

        answered = false;
        
        String response = answer?"yes":"no";
        Evidence e = new Evidence(ev, response);
        DefectDiagnostic.KS.insert(e);

        if (response.compareTo(v) == 0) {
            DefectDiagnostic.agendaEventListener.addLhs(e);
            return true;
        } else {
            DefectDiagnostic.agendaEventListener.resetLhs();
            return false;
        }
    }

}
