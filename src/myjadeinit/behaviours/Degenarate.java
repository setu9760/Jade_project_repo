/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import myjadeinit.extras.SystemSize;

/**
 *
 * @author Desai
 */
public class Degenarate extends Behaviour {

    private final SystemSize size;
    private int degenerateBy = -1;

    /**
     * This constructor is used to provide regular degeneration of the software
     * system as it provides determinate degeneration, meaning the system size
     * will decrease by one unit every time this behaviour is applied using this
     * constructor.
     *
     * @param agent
     * @param size
     */
    public Degenarate(Agent agent, SystemSize size) {
        super(agent);
        this.size = size;
    }

    /**
     * This constructor is used to provide indeterminate degeneration of the
     * software system, meaning the system size will decrease by random number
     * every time te behaviour is applied using this constructor.
     *
     * @param agent
     * @param size
     * @param degenerateBy
     */
    public Degenarate(Agent agent, SystemSize size, int degenerateBy) {
        super(agent);
        this.size = size;
        this.degenerateBy = degenerateBy;
    }

    @Override
    public void action() {
        while (done()) {
            if (size.getSoftSize() == 0) {
                myAgent.doDelete();
            } else {
                size.decreaseSize();
                System.out.println("Software size is: " + size.getSoftSize());
                degenerateBy--;
            }
        }
    }

    @Override
    public boolean done() {
        return degenerateBy > 0;
    }
}
