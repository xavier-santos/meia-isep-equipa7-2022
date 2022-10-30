package org.engcia.utils;

import org.engcia.diagnostic.TrackingAgendaEventListener;
import org.engcia.model.Conclusion;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.LiveQuery;
import org.kie.api.runtime.rule.Row;
import org.kie.api.runtime.rule.ViewChangedEventListener;

public class Engine extends Thread{

    private final String droolsFileName;
    public static KieSession KS;
    public static TrackingAgendaEventListener agendaEventListener;

    public Engine(String droolsFileName){
        this.droolsFileName = droolsFileName;
    }

    @Override
    public void run(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        final KieSession kSession = kContainer.newKieSession(droolsFileName);
        KS = kSession;
        agendaEventListener = new TrackingAgendaEventListener();
        kSession.addEventListener(agendaEventListener);

        ViewChangedEventListener listener = new ViewChangedEventListener() {
            @Override
            public void rowDeleted(Row row) {
            }

            @Override
            public void rowInserted(Row row) {
                Conclusion conclusion = (Conclusion) row.get("$conclusion");
                System.out.println(">>>" + conclusion.toString());

                // stop inference engine after as soon as got a conclusion
                kSession.halt();

            }

            @Override
            public void rowUpdated(Row row) {
            }

        };

        LiveQuery query = kSession.openLiveQuery("Conclusions", null, listener);

        kSession.fireAllRules();

        query.close();
    }
}
