package org.engcia.diagnostic;

import org.engcia.model.Justification;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Map;


@SpringBootApplication
public class DefectDiagnostic {
    public static KieSession KS;
    public static TrackingAgendaEventListener agendaEventListener;
    public static Map<Integer, Justification> justifications;
    public static KieServices ks;
    public static KieSession kSession;
    private static BufferedReader br;

    public static void main(String[] args){
       SpringApplication.run(DefectDiagnostic.class, args);
    }

    public static void runEngine(String kieSession){
        DroolsEngine droolsEngine = new DroolsEngine(kieSession);
        droolsEngine.start();
    }

}

