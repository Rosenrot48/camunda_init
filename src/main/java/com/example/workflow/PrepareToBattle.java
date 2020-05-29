package com.example.workflow;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class PrepareToBattle implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        boolean win = false;
        int warriors = (int) delegateExecution.getVariable("warriors");
        int enemyWarriors = (int) (Math.random() * 100);

        String battleStatus = "Undefined";

        if (warriors <= 0 || warriors > 100) {
            throw  new BpmnError("errorWarriors");
        }

        if ((warriors - enemyWarriors) > 0) {
            win = true;
            battleStatus = "Victory!";
        } else {
            battleStatus = "Defeat";
        }

        delegateExecution.setVariable("enemyWarriors", enemyWarriors);
        delegateExecution.setVariable("battleStatus", battleStatus);
        delegateExecution.setVariable("win", win);


    }
}
