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
package behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import utils.SystemSize;

/**
 *
 * @author Desai
 */
public class Degenarate extends Behaviour {

    private final SystemSize size;
    private int degenerateBy = -1;

    /**
     * This constructor is used to provide regular degeneration of the software
     * system as it provides determinate degeneration, meaning the system size
     * will decrease by one unit every time this behaviour is applied using this
     * constructor.
     *
     * @param agent
     * @param size
     */
    public Degenarate(Agent agent, SystemSize size) {
        super(agent);
        this.size = size;
    }

    /**
     * This constructor is used to provide indeterminate degeneration of the
     * software system, meaning the system size will decrease by random number
     * every time the behaviour is applied using this constructor.
     *
     * @param agent
     * @param size
     * @param degenerateBy
     */
    public Degenarate(Agent agent, SystemSize size, int degenerateBy) {
        super(agent);
        this.size = size;
        this.degenerateBy = degenerateBy;
    }

    @Override
    public void action() {
        while (done()) {
            if (size.getSoftSize() == 0) {
                myAgent.doDelete();
            } else {
                size.decreaseSize();
                System.out.println("Software size is: " + size.getSoftSize());
                degenerateBy--;
            }
        }
    }

    @Override
    public boolean done() {
        return degenerateBy > 0;
    }
}
