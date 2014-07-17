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
public class Evolve extends Behaviour {

    private int evolutionCount;
    private final SystemSize size;

    public Evolve(Agent agent, int evolutionRate, SystemSize size) {
        super(agent);
        this.evolutionCount = evolutionRate;
        this.size = size;
    }

    @Override
    public void action() {
        while (done()) {
            size.increaseSize();
            System.out.println("Software size is: " + size.getSoftSize());
            evolutionCount--;
        }
    }

    @Override
    public boolean done() {
        return evolutionCount > 0;
    }

}
