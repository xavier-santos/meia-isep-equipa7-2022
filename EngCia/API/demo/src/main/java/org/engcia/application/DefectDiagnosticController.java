package org.engcia.application;

import org.engcia.DefectDiagnostic;
import org.engcia.Dto.ResponseDto;
import org.engcia.diagnostic.DroolsEngine;
import org.engcia.view.QuestionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.Thread.sleep;

@RestController
public class DefectDiagnosticController {

    @Autowired
    public DefectDiagnosticController() {
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/loadEngine",
            method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResponseDto> loadEngine(@RequestParam String kieSession) throws InterruptedException {
        DefectDiagnostic.runEngine(kieSession);
        while (!QuestionFactory.newQuestion && !DroolsEngine.theEnd) {
            sleep(500);
        }
        return ResponseEntity.ok(QuestionFactory.question);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/nextStep",
            method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResponseDto> nextStep(@RequestParam boolean response) throws InterruptedException {
        QuestionFactory.answer = response;
        QuestionFactory.answered = true;
        QuestionFactory.newQuestion = false;
        while (!QuestionFactory.newQuestion && !DroolsEngine.theEnd) {
            sleep(500);
        }
        DroolsEngine.theEnd = false;
        return ResponseEntity.ok(QuestionFactory.question);
    }
}
