package org.engcia.controller;

import org.engcia.diagnostic.DefectDiagnostic;
import org.engcia.diagnostic.DroolsEngine;
import org.engcia.view.QuestionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Locale;

import static java.lang.Thread.sleep;

@RestController
public class DefectDiagnosticController {

    @Autowired
    public DefectDiagnosticController() {
    }

    @CrossOrigin
    @RequestMapping(value = "/loadEngine",
            method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> loadEngine(@RequestParam String kieSession) throws InterruptedException {
        DefectDiagnostic.runEngine(kieSession);
        while (!QuestionFactory.newQuestion) {
            sleep(500);
        }
        return ResponseEntity.ok(QuestionFactory.question);
    }

    @CrossOrigin
    @RequestMapping(value = "/nextStep",
            method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> nextStep(@RequestParam boolean response) throws InterruptedException {
        QuestionFactory.answer = response;
        QuestionFactory.answered = true;
        QuestionFactory.newQuestion = false;
        while (!QuestionFactory.newQuestion && !DroolsEngine.theEnd) {
            sleep(500);
        }
        if(DroolsEngine.theEnd){
            return ResponseEntity.ok("the end");
        }
        return ResponseEntity.ok(QuestionFactory.question);
    }
}
