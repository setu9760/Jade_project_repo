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
public class Evolve extends Behaviour {

    private int evoleBy = 1;
    private final SystemSize size;

    /**
     * This constructor is mainly used when making normal evolution process to
     * the software system. This constructor provides determinate evolution rate
     * for the system as it only evolves the system by one unit.
     *
     * @param agent: my agent
     * @param size: system size parameter
     */
    public Evolve(Agent agent, SystemSize size) {
        super(agent);
        this.size = size;
    }

    /**
     * This constructor is used when
     * {@link myjadeinit.behaviours.RandomEvolution} is applied to the software
     * system. This constructor provides indeterminate evolution to the software
     * system.
     *
     * @param agent: my agent
     * @param size: system size parameter
     * @param evolveBy: random integer 
     */
    public Evolve(Agent agent, SystemSize size, int evolveBy) {
        super(agent);
        this.size = size;
        this.evoleBy = evolveBy;
    }

    @Override
    public void action() {
        while (done()) {
            size.increaseSize();
            System.out.println("Software size is: " + size.getSoftSize());
            evoleBy--;
        }
    }

    @Override
    public boolean done() {
        return evoleBy > 0;
    }

}
