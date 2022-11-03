package org.engcia.diagnostic;

import org.engcia.model.Conclusion;
import org.engcia.model.Justification;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.rule.LiveQuery;
import org.kie.api.runtime.rule.Row;
import org.kie.api.runtime.rule.ViewChangedEventListener;

import java.util.TreeMap;

import static org.engcia.diagnostic.DefectDiagnostic.ks;

public class DroolsEngine extends Thread{

    private String fileName;
    public static boolean theEnd=false;

    public DroolsEngine(String fileName){
        this.fileName = fileName;
    }

    public void run(){

        try {
            DefectDiagnostic.justifications = new TreeMap<Integer, Justification>();
            ks = KieServices.Factory.get();
            KieContainer kContainer = DefectDiagnostic.ks.getKieClasspathContainer();
            DefectDiagnostic.kSession = kContainer.newKieSession(fileName);
            DefectDiagnostic.KS = DefectDiagnostic.kSession;
            DefectDiagnostic.agendaEventListener = new TrackingAgendaEventListener();
            DefectDiagnostic.kSession.addEventListener(DefectDiagnostic.agendaEventListener);

            ViewChangedEventListener listener = new ViewChangedEventListener() {
                @Override
                public void rowDeleted(Row row) {
                }

                @Override
                public void rowInserted(Row row) {
                    Conclusion conclusion = (Conclusion) row.get("$conclusion");
                    System.out.println(">>>" + conclusion.toString());

                    How how = new How(DefectDiagnostic.justifications);
                    System.out.println(how.getHowExplanation(conclusion.getId()));

                    DefectDiagnostic.kSession.halt();

                    theEnd = true;

                }

                @Override
                public void rowUpdated(Row row) {
                }

            };

            LiveQuery query = DefectDiagnostic.kSession.openLiveQuery("Conclusions", null, listener);

            DefectDiagnostic.kSession.fireAllRules();

            query.close();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
