package org.engcia.diagnostic;

import org.drools.core.event.DefaultAgendaEventListener;
import org.engcia.DefectDiagnostic;
import org.engcia.model.Fact;
import org.engcia.model.Justification;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.*;
import org.kie.api.runtime.rule.Match;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("restriction")
public class TrackingAgendaEventListener extends DefaultAgendaEventListener{
    private List<Match> matchList = new ArrayList<Match>();
    private List<Fact> lhs = new ArrayList<Fact>();
    private List<Fact> rhs = new ArrayList<Fact>();

    public void resetLhs() {
        lhs.clear();
    }

    public void addLhs(Fact f) {
        lhs.add(f);
    }

    public void resetRhs() {
        rhs.clear();
    }

    public void addRhs(Fact f) {
        rhs.add(f);
    }

    @Override
    public void matchCreated(MatchCreatedEvent event) {
    }
    @Override
    public void matchCancelled(MatchCancelledEvent event) {
    }
    @Override
    public void beforeMatchFired(BeforeMatchFiredEvent event) {
    }
    @Override
    public void agendaGroupPushed(AgendaGroupPushedEvent event) {
    }
    @Override
    public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
    }
    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        Rule rule = event.getMatch().getRule();
        String ruleName = rule.getName();
        List <Object> list = event.getMatch().getObjects();
        for (Object e : list) {
            if (e instanceof Fact) {
                lhs.add((Fact)e);
            }
        }

        for (Fact f: rhs) {
            Justification j = new Justification(ruleName, lhs, f);
            int id = f.getId();
            DefectDiagnostic.justifications.put(id, j);
        }

        resetLhs();
        resetRhs();
    }
}
