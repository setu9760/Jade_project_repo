/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import myjadeinit.extras.SourceCodeQuality;

/**
 *
 * @author Desai
 */
public class Refactor extends Behaviour {

    private final SourceCodeQuality codeQuality;
    private boolean done = false;

    public Refactor(Agent agent, SourceCodeQuality codeQuality) {
        super(agent);
        this.codeQuality = codeQuality;
    }

    @Override
    public void action() {
        while (!done()) {
            codeQuality.increaseQuality();
            System.out.println("Software code quality is : " + codeQuality.getCodeQuality());

            done = true;
        }
    }

    @Override
    public boolean done() {
        return done;
    }

}
