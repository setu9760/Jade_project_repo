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
import myjadeinit.extras.SystemSize;

/**
 *
 * @author Desai
 */
public class Degenarate extends Behaviour {

    private final SystemSize size;
    private int degenarateCounter;

    public Degenarate(Agent agent, int degenarateCounter, SystemSize size) {
        super(agent);
        this.degenarateCounter = degenarateCounter;
        this.size = size;
    }

    @Override
    public void action() {
        while (done()) {
            if (size.getSoftSize() == 0) {
                myAgent.doDelete();
            } else {
                size.decreaseSize();
                System.out.println("Software size is: " + size.getSoftSize());
                degenarateCounter--;
            }
        }
    }

    @Override
    public boolean done() {
        return degenarateCounter > 0;
    }
}
