/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Desai
 */
public class DeveloperBehaviour extends Behaviour {

    @Override
    public void action() {
        
        ACLMessage msg = myAgent.receive();
        if (msg!=null) {
            String message = msg.getContent();
            ACLMessage reply = msg.createReply();
            
        }
        
    }

    @Override
    public boolean done() {
       return false;
    }

}
