package org.engcia.diagnostic;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.LiveQuery;
import org.kie.api.runtime.rule.Row;
import org.kie.api.runtime.rule.ViewChangedEventListener;

import org.engcia.model.Conclusion;
import org.engcia.model.Justification;
import org.engcia.view.UI;

public class DefectDiagnostic {
    public static KieSession KS;
    public static BufferedReader BR;
    public static TrackingAgendaEventListener agendaEventListener;
    public static Map<Integer, Justification> justifications;

    public static final void main(String[] args) {
        UI.uiInit();
        selectDefect();
        UI.uiClose();
    }

    private static void selectDefect() {
        System.out.println("Write number of current defect:");
        System.out.println("1. Ripped foam part");
        System.out.println("2. Collapsed foam part");
        System.out.println("3. Bubbled foam part");
        System.out.println("4. Part missing");
        System.out.println("5. Lack of filling");
        System.out.println("6. Misplaced component");
        System.out.println("0. Exit");
        String option = UI.readLine();

        switch (option) {
            case "1":
                runEngine("ksession-rules-ripped");
                break;
            case "2":
                runEngine("ksession-rules-collapsed");
                break;
            case "3":
                runEngine("ksession-rules-bubbled");
                break;
            case "4":
                runEngine("ksession-rules-lackcomponent");
                break;
            case "5":
                runEngine("ksession-rules-lackfilling");
                break;
            case "6":
                runEngine("ksession-rules-misplaced");
                break;
            case "0":
                System.out.println("Exiting program...");
                break;
            default:
                System.out.println("Selected option is invalid. Exiting program...");
                break;
        }
    }


    private static void runEngine(String kieSession) {
        try {
            DefectDiagnostic.justifications = new TreeMap<Integer, Justification>();

            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            final KieSession kSession = kContainer.newKieSession(kieSession);
            DefectDiagnostic.KS = kSession;
            DefectDiagnostic.agendaEventListener = new TrackingAgendaEventListener();
            kSession.addEventListener(agendaEventListener);

            // Query listener
            ViewChangedEventListener listener = new ViewChangedEventListener() {
                @Override
                public void rowDeleted(Row row) {
                }

                @Override
                public void rowInserted(Row row) {
                    Conclusion conclusion = (Conclusion) row.get("$conclusion");
                    System.out.println(">>>" + conclusion.toString());

                    //System.out.println(DefectDiagnostic.justifications);
                    How how = new How(DefectDiagnostic.justifications);
                    System.out.println(how.getHowExplanation(conclusion.getId()));

                    // stop inference engine after as soon as got a conclusion
                    kSession.halt();

                }

                @Override
                public void rowUpdated(Row row) {
                }

            };

            LiveQuery query = kSession.openLiveQuery("Conclusions", null, listener);

            kSession.fireAllRules();
            // kSession.fireUntilHalt();

            query.close();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}

