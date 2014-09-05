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
package abstracts;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

/**
 *
 * @author S Desai
 */
public abstract class AbstractActor extends Agent {

    protected CyclicBehaviour ReceiveMessage;

    @Override
    protected void takeDown() {
        System.out.println("Agent " + getLocalName() + " is terminated");
        doSuspend();
        doDelete();
        super.takeDown();
    }

    protected final void welcomMessage() {
        System.out.println("Agent: " + getLocalName() + " is created.");
    }

    @Override
    protected abstract void setup();

}
