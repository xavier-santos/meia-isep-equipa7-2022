package org.engcia.model;

import java.util.ArrayList;
import java.util.List;

public class Justification {
    private final String rule;
    private final List<Fact> lhs;
    private final Fact conclusion;

    public Justification(String rule, List<Fact> lhs, Fact conclusion) {
        this.rule = rule;
        this.lhs = new ArrayList<Fact>(lhs);
        this.conclusion = conclusion;
    }

    public String getRuleName() {
        return this.rule;
    }

    public List<Fact> getLhs() {
        return this.lhs;
    }

    public Fact getConclusion() {
        return this.conclusion;
    }
}
