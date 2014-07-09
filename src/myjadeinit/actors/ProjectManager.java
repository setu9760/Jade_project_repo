/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.actors;

import jade.core.Agent;

/**
 *
 * @author Desai
 */
public class ProjectManager extends Agent {

    @Override
    protected void setup() {
        System.out.println("Hello World!!!! \n Agent: " + getLocalName() + " is created, full name: " + getName());
    }

}
