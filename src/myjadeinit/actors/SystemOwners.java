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
package myjadeinit.actors;

import jade.core.Agent;
import myjadeinit.behaviours.AbstractReceiveMessage;

/**
 *
 * @author Desai
 */
public class SystemOwners extends Agent {

    private ReceiveMessage receiveMessageBehaviour;

    @Override
    protected void setup() {
        System.out.println("Agent: " + getLocalName() + " is created.");
        receiveMessageBehaviour = new ReceiveMessage(this);
        addBehaviour(receiveMessageBehaviour);
    }

    @Override
    protected void takeDown() {
        System.out.println("The agent: " + getLocalName() + " has been terminated");
        doDelete();
        super.takeDown();
    }

    private class ReceiveMessage extends AbstractReceiveMessage {

        public ReceiveMessage(Agent agent) {
            super(agent);
        }

        @Override
        public void action() {
            myAgent = (SystemOwners) myAgent;
            aclmessage = myAgent.receive();
            if (aclmessage != null) {
                message = aclmessage.getContent().toLowerCase(locale);
                printMessage(aclmessage.getSender(), message);
                switch (message) {
                    /**
                     * Do something here if system owner receives particular
                     * message.
                     */

                }
            }
            aclmessage = null;
        }

    }

}
