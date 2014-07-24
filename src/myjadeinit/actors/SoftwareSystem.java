/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.actors;

import jade.core.Agent;
import myjadeinit.behaviours.ReceiveMessage;
import myjadeinit.extras.SystemSize;

/**
 *
 * @author Desai
 */
public class SoftwareSystem extends Agent {

    public final SystemSize size;
    private ReceiveMessage receiveMessageBehaviour;

    public SoftwareSystem() {
        this.size = new SystemSize(50);
    }

    @Override
    protected void setup() {
        System.out.println("Hello World!!!! \n Agent: " + getLocalName() + " is created, full name: " + getName());
        receiveMessageBehaviour = new ReceiveMessage(this, size);
        /**
         * Adding a cyclic behaviour to the agent which detects incoming
         * messages.
         */
        addBehaviour(receiveMessageBehaviour);

    }

    @Override
    protected void takeDown() {
        System.out.println("Agent " + getLocalName() + " is terminated");
        doDelete();
        super.takeDown();
    }

}
