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
public class Defactor extends Behaviour {

    /**
     */
    private final SourceCodeQuality codeQuality;
    private boolean done = false;
    private int DefactorBy = 0;

    /**
     *
     * @param agent
     * @param codeQuality
     */
    public Defactor(Agent agent, SourceCodeQuality codeQuality) {
        super(agent);
        this.codeQuality = codeQuality;
    }

    /**
     *
     * @param agent
     * @param codeQuality
     * @param DefactorBy
     */
    public Defactor(Agent agent, SourceCodeQuality codeQuality, int DefactorBy) {
        super(agent);
        this.codeQuality = codeQuality;
        this.DefactorBy = DefactorBy;
    }

    /**
     *
     */
    @Override
    public void action() {
        while (!done()) {

            if (codeQuality.getCodeQuality() == 1) {
                ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
                aclMessage.addReceiver(new AID("System", AID.ISLOCALNAME));
                aclMessage.setContent("die");
                myAgent.send(aclMessage);
                myAgent.doDelete();
            } else {
                if (this.DefactorBy != 0) {
                    codeQuality.decreaseQuality(DefactorBy);
                    System.out.println("Quality decreased by: " + DefactorBy);
                    System.out.println("Code Quality is: " + codeQuality.getCodeQuality());
                } else {
                    codeQuality.decreaseQuality();
                    System.out.println("Code Quality is: " + codeQuality.getCodeQuality());
                }
            }
            done = true;
        }
    }

    @Override
    public boolean done() {
        return done;
    }

}
