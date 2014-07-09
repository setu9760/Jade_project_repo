/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.actors;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
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

    @Override
    protected void setup() {

        System.out.println("Hello World!!!! \n Agent: " + getLocalName() + " is created, full name: " + getName());
        Object[] args = getArguments();

        if (args != null) {
            if (args.length != 3) {
                takeDown();
            } else {
                size = new SystemSize(Integer.parseInt((String) args[0]));
                counter = Integer.parseInt((String) args[1]);
                string = (String) args[2];

                if (string.equalsIgnoreCase("evolve")) {
                    behaviour = new Evolve(counter);
                    addBehaviour(behaviour);
                    behaviour.action();
                } else if (string.equalsIgnoreCase("degenerate")) {
                    behaviour = new Degenarate(counter);
                    behaviour.action();
                }
            }

        }
    }

    @Override
    protected void takeDown() {
        System.out.println("Initialisation requires 3 arguments. this agent will be destroyed now");
        super.takeDown(); //To change body of generated methods, choose Tools | Templates.
    }

    private class Degenarate extends Behaviour {

        private int degenarateCounter;

        public Degenarate(int degenarateCounter) {
            this.degenarateCounter = degenarateCounter;
        }

        @Override
        public void action() {
            while (done()) {
                size.decreaseSize();
                System.out.println("Software size is: " + size.getSoftSize());
            }
        }

        @Override
        public boolean done() {
            if (degenarateCounter > 0) {
                degenarateCounter--;
                return true;
            }
            return false;
        }
    }

    private class Evolve extends Behaviour {

        private int evolutionCount;

        public Evolve(int evolutionRate) {
            this.evolutionCount = evolutionRate;
        }

        @Override
        public void action() {
            while (done()) {
                size.increaseSize();
                System.out.println("Software size is: " + size.getSoftSize());
            }

        }

        @Override
        public boolean done() {
            if (evolutionCount > 0) {
                evolutionCount--;
                return true;
            }
            return false;
        }

    }

}
