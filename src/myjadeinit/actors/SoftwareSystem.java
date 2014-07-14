/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.actors;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import myjadeinit.extras.SystemSize;

/**
 *
 * @author Desai
 */
public class SoftwareSystem extends Agent {
    
    private SystemSize size;
    private String string;
    private int counter;
    private Behaviour behaviour;
    private ACLMessage msg;
    
    @Override
    protected void setup() {
        
        System.out.println("Hello World!!!! \n Agent: " + getLocalName() + " is created, full name: " + getName());
        Object[] args = getArguments();
        
        if (args != null) {
            if (args.length != 3) {
                takeDown();
            } else {
                size = new SystemSize(Integer.parseInt((String) args[0]));
                System.out.println("Software size is: " + size.getSoftSize());
                counter = Integer.parseInt((String) args[1]);
                string = (String) args[2];
                
                if (string.equalsIgnoreCase("evolve")) {
                    behaviour = new Evolve(this, counter);
                    addBehaviour(behaviour);
                    //behaviour.action();
                } else if (string.equalsIgnoreCase("degenerate")) {
                    behaviour = new Degenarate(this, counter);
                    addBehaviour(behaviour);
                    //behaviour.action();
                }
            }
        }
        msg = receive();
        if (msg != null) {
            if (msg.getContent().equalsIgnoreCase("evolve")) {
                addBehaviour(new Evolve(this, 5));
            }
        }
    }
    
    @Override
    protected void takeDown() {
        System.out.println("Agent " + getLocalName() + " is terminated");
        doDelete();
        super.takeDown(); //To change body of generated methods, choose Tools | Templates.
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
                    break;
                } else {
                    size.decreaseSize();
                    System.out.println("Software size is: " + size.getSoftSize());
                    degenarateCounter--;
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
            }
        }
        
        @Override
        public boolean done() {
            return evolutionCount > 0;
        }
    }
}
