/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.actors;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import myjadeinit.behaviours.DeveloperBehaviour;

/**
 *
 * @author Desai
 */
public class Developer extends Agent {

    Behaviour behaviour;
    
    @Override
    protected void setup() {
        System.out.println("Hello World!!!! \n Agent: " + getLocalName() + " is created.");
        behaviour = new DeveloperBehaviour();
        addBehaviour(behaviour);
    }

    @Override
    protected void takeDown() {
        super.takeDown();
    }

    @Override
    public void doDelete() {
        takeDown();
    }

}
