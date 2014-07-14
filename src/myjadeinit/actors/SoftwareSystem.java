/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.actors;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.ACLParserConstants;
import java.util.logging.Level;
import java.util.logging.Logger;
import myjadeinit.extras.SystemSize;

/**
 *
 * @author Desai
 */
public class SoftwareSystem extends Agent {

    private final SystemSize size = new SystemSize(1);
    private final String EVOLVE = "evolve";
    private final String DEGENERATE = "degenerate";

    @Override
    protected void setup() {

        System.out.println("Hello World!!!! \n Agent: " + getLocalName() + " is created, full name: " + getName());

        /**
         * Adding a cyclic behaviour to the agent which detects incoming
         * messages.
         */
        addBehaviour(new ReceiveMessage(this));

    }

    @Override
    protected void takeDown() {
        System.out.println("Agent " + getLocalName() + " is terminated");
        doDelete();
        super.takeDown();
    }

    /**
     * A cyclic behaviour class that lets the agent receive messages one by one
     * until the agent is alive and adds new behaviours to it according to the
     * message.
     */
    private class ReceiveMessage extends CyclicBehaviour {

        private ReceiveMessage(Agent agent) {
            super(agent);
        }

        @Override
        public void action() {
            ACLMessage aclMessage = receive();
            if (aclMessage != null) {
                String message = aclMessage.getContent();
                System.out.println("Receieved message:");
                System.out.println(aclMessage.getSender() + ": " + message);
                if (message.equalsIgnoreCase(EVOLVE)) {
                    addBehaviour(new Evolve(myAgent, 1));
                } else if (message.equalsIgnoreCase(DEGENERATE)) {
                    addBehaviour(new Degenarate(myAgent, 1));
                }
            }
            block();
        }

    }

    private class Degenarate extends Behaviour {

        private int degenarateCounter;

        public Degenarate(Agent agent, int degenarateCounter) {
            super(agent);
            this.degenarateCounter = degenarateCounter;
        }

        @Override
        public void action() {
            while (done()) {
                if (size.getSoftSize() == 0) {
                    takeDown();
                } else {
                    size.decreaseSize();
                    System.out.println("Software size is: " + size.getSoftSize());
                    degenarateCounter--;
                    ACLMessage aclmessage = new ACLMessage(ACLMessage.INFORM);
                    aclmessage.setContent("degenerated successfully");
                    aclmessage.addReceiver(new AID("Developer", AID.ISLOCALNAME));
                    send(aclmessage);
                }
            }
        }

        @Override
        public boolean done() {
            return degenarateCounter > 0;
        }
    }

    private class Evolve extends Behaviour {

        private int evolutionCount;

        public Evolve(Agent agent, int evolutionRate) {
            super(agent);
            this.evolutionCount = evolutionRate;
        }

        @Override
        public void action() {
            while (done()) {
                size.increaseSize();
                System.out.println("Software size is: " + size.getSoftSize());
                evolutionCount--;
                ACLMessage aclmessage = new ACLMessage(ACLMessage.INFORM);
                aclmessage.setContent("evolved successfully");
                aclmessage.addReceiver(new AID("Developer", AID.ISLOCALNAME));
                send(aclmessage);
            }
        }

        @Override
        public boolean done() {
            return evolutionCount > 0;
        }
    }
}
