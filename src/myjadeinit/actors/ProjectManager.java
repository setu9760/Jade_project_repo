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
public class ProjectManager extends Agent {

    private ReceiveMessage receiveMessageBehaviour;

    @Override
    protected void setup() {
        System.out.println("Hello World!!!! \n Agent: " + getLocalName() + " is created, full name: " + getName());
        receiveMessageBehaviour = new ReceiveMessage(this);
        addBehaviour(receiveMessageBehaviour);
    }

    private class ReceiveMessage extends AbstractReceiveMessage {

        public ReceiveMessage(Agent agent) {
            super(agent);
        }

        @Override
        public void action() {
            myAgent = (ProjectManager) myAgent;
            aclmessage = myAgent.receive();
            if (aclmessage != null) {
                message = aclmessage.getContent().toLowerCase(locale);

                switch (message) {
                    case EVOLVE:
                        sendMessage(DEVELOPER_AID, EVOLVE, DEFAULT_MESSAGE_TYPE);
                        break;
                    case REQUEST_REQUIREMENT_CHANGE:
                        sendMessage(DEVELOPER_AID, REQUEST_REQUIREMENT_CHANGE, REQUEST_MESSAGE_TYPE);
                        break;
                    case ACCEPT_REQUIREMENT_CHANGE:
                        //TODO:
                        break;
                    case DECLINE_REQUIREMENT_CHANGE:
                        //TODO:
                        break;
                    case DIE_MESSAGE:
                        myAgent.doSuspend();
                        myAgent.doDelete();
                        break;
                }
            }
            aclmessage = null;
        }

    }

}
