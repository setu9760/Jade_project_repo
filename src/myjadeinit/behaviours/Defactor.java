/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import myjadeinit.extras.SourceCodeQuality;

/**
 *
 * @author Desai
 */
public class Defactor extends Behaviour {

    private final SourceCodeQuality codeQuality;
    private boolean done = false;

    public Defactor(Agent agent, SourceCodeQuality codeQuality) {
        super(agent);
        this.codeQuality = codeQuality;
    }

    @Override
    public void action() {
        while (!done()) {
            if (codeQuality.getCodeQuality() <= 0) {
                myAgent.doDelete();
            } else {
                codeQuality.decreaseQuality();
            }
            done = true;
        }
    }

    @Override
    public boolean done() {
        return done;
    }

}
