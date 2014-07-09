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
public class Sponsors extends Agent {

    @Override
    protected void setup() {
        System.out.println("Hello World!!!! \n Agent: " + getLocalName() + " is created, full name: " + getName());
        Object[] args = getArguments();
        String s;
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                s = (String) args[i];
                System.out.println("p" + i + ": " + s);
            }

            // Extracting the integer.
            int i = Integer.parseInt((String) args[0]);
            System.out.println("i*i= " + (i * i));

        }
    }

    @Override
    protected void takeDown() {
        System.out.println("The agent: " + getLocalName() + " has been terminated");
    }

}
