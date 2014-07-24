/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myjadeinit.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import myjadeinit.actors.SoftwareSystem;
import myjadeinit.extras.SystemSize;

/**
 *
 * @author Desai
 */
public class RandomChangeRequest extends Behaviour{

    private final SystemSize size;
    private int evolveBy;

    /**
     * 
     * @param agent
     * @param size
     * @param evolceBy 
     */
    public RandomChangeRequest( Agent agent,SystemSize size, int evolceBy) {
        super(agent);
        this.size = size;
        this.evolveBy = evolceBy;
    }
    
    
    
    @Override
    public void action() {
        
    }

    @Override
    public boolean done() {
        return false;
    }
    
}
