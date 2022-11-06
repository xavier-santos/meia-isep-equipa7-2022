package org.engcia;

import org.engcia.application.DefectDiagnosticController;
import org.engcia.diagnostic.DroolsEngine;
import org.engcia.diagnostic.TrackingAgendaEventListener;
import org.engcia.model.Justification;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Map;


@SpringBootApplication
@ComponentScan(basePackageClasses = DefectDiagnosticController.class)
public class DefectDiagnostic {
    public static KieSession KS;
    public static TrackingAgendaEventListener agendaEventListener;
    public static Map<Integer, Justification> justifications;
    public static KieServices ks;
    public static KieSession kSession;

    public static void main(String[] args){
        System.setProperty("server.port", "4041");
       SpringApplication.run(DefectDiagnostic.class, args);
    }

    public static void runEngine(String kieSession){
        DroolsEngine droolsEngine = new DroolsEngine(kieSession);
        droolsEngine.start();
    }

}





