/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.actors;

import jade.core.Agent;
import myjadeinit.behaviours.ReceiveMessage;
import myjadeinit.extras.SourceCodeQuality;

/**
 *
 * @author Desai
 */
public class SourceCode extends Agent {

    private final SourceCodeQuality codeQuality = new SourceCodeQuality(5);
    private ReceiveMessage receiveMesageBehaviour;

    @Override
    protected void setup() {
        System.out.println("Hello World!!!! \n Agent: " + getLocalName() + " is created.");
        receiveMesageBehaviour = new ReceiveMessage(this, codeQuality);
        addBehaviour(receiveMesageBehaviour);
    }

}
