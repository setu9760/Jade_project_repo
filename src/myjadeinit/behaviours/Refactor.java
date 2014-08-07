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
public class Refactor extends Behaviour {

    /**
     *
     */
    private final SourceCodeQuality codeQuality;

    private boolean done = false;
    private int RefactorBy = 0;

    /**
     *
     * @param agent
     * @param codeQuality
     */
    public Refactor(Agent agent, SourceCodeQuality codeQuality) {
        super(agent);
        this.codeQuality = codeQuality;
    }

    /**
     *
     * @param agent
     * @param CodeQuality
     * @param RefactorBy
     */
    public Refactor(Agent agent, SourceCodeQuality CodeQuality, int RefactorBy) {
        super(agent);
        this.codeQuality = CodeQuality;
        this.RefactorBy = RefactorBy;
    }

    @Override
    public void action() {
        while (!done()) {

            if (RefactorBy != 0) {
                codeQuality.increaseQuality(RefactorBy);
                System.out.println("Code Quality increased by: " + RefactorBy);
                System.out.println("Code Quality is: " + codeQuality.getCodeQuality());
            } else {
                codeQuality.increaseQuality();
                System.out.println("Code Quality is: " + codeQuality.getCodeQuality());
            }
            done = true;
        }
    }

    @Override
    public boolean done() {
        return done;
    }

}
