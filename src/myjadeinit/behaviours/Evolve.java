/* 
 * Copyright (C) 2014 S Desai
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package myjadeinit.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import myjadeinit.extras.SystemSize;

/**
 *
 * @author Desai
 */
public class Evolve extends Behaviour {

    private int evoleBy = 0;
    private final SystemSize size;
    private boolean done = false;

    /**
     * This constructor is mainly used when making normal evolution process to
     * the software system. This constructor provides determinate evolution rate
     * for the system as it only evolves the system by one unit.
     *
     * @param agent: my agent
     * @param size: system size parameter
     */
    public Evolve(Agent agent, SystemSize size) {
        super(agent);
        this.size = size;
    }

    /**
     * This constructor is used when
     * {@link myjadeinit.behaviours.RandomEvolution} is applied to the software
     * system. This constructor provides evolution to the software system based
     * on random number generated passed in the constructor..
     *
     * @param agent: my agent
     * @param size: system size parameter
     * @param evolveBy: random integer
     */
    public Evolve(Agent agent, SystemSize size, int evolveBy) {
        super(agent);
        this.size = size;
        this.evoleBy = evolveBy;
    }

    @Override
    public void action() {
        while (!done()) {
            if (this.evoleBy != 0) {
                size.increaseSize(evoleBy);
                System.out.println("evolved by: " + evoleBy);
                System.out.println("Software size is: " + size.getSoftSize());
            } else {
                size.increaseSize();
                System.out.println("Software size is: " + size.getSoftSize());
            }
            done = true;
        }
    }

    @Override
    public boolean done() {
        return done;
    }

}
