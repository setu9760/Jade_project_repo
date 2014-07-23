/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import java.util.Random;
import myjadeinit.extras.SystemSize;

/**
 *
 * @author Desai
 */
public class RandomEvolution extends Behaviour {

    private final SystemSize size;
    private final int MAX = 1;
    private final int MIN = 0;
    private int evolveBy = 1;

    public RandomEvolution(Agent agent, SystemSize size) {
        super(agent);
        this.size = size;
    }

    @Override
    public void action() {

        switch (Randomize(MAX, MIN)) {
            case 0:
                /*
                 If case is 0 then the evolution goes negative meaning that the implementation 
                 of requirement change hasn't been successful resulting in negative system evolution
                 */
                /*
                 NOTE evlolution in this situation happens in indeterminate manner, meaning it may 
                 evolve negatively by more than one unit.
                 */

                break;
            case 1:
                /*
                 If case is 1 then the evolution goes positive meaning that the implementation 
                 of new requirement has been successfull resulting in positive system evolution
                 */
                /*
                 NOTE evlolution in this situation happens in indeterminate manner, meaning it may 
                 evolve by more than one unit
                 */

                while (done()) {
                    // myAgent.addBehaviour(new Evolve(myAgent, size, evolveBy));
                }

                break;
        }

    }

    @Override
    public boolean done() {
        return evolveBy > 0;
    }

    public int Randomize(int MAX, int MIN) {

        Random random = new Random();

        int randomeNum = random.nextInt((MAX - MIN) + 1) + MIN;

        return randomeNum;
    }

}
