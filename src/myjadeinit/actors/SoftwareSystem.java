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
import java.util.logging.Level;
import java.util.logging.Logger;
import myjadeinit.behaviours.AbstractReceiveMessage;
import myjadeinit.behaviours.Degenarate;
import myjadeinit.behaviours.Evolve;
import myjadeinit.extras.InitialiseVariable;
import myjadeinit.extras.SystemSize;

/**
 *
 * @author Desai
 */
public class SoftwareSystem extends Agent {

    public SystemSize size;
    private ReceiveMessage receiveMessageBehaviour;

    public SoftwareSystem() {
        this.size = new SystemSize(InitialiseVariable.SoftSize);
    }

    @Override
    protected void setup() {
        System.out.println("Hello World!!!! \n Agent: " + getLocalName() + " is created, full name: " + getName());

        receiveMessageBehaviour = new ReceiveMessage(this);
        /**
         * Adding a cyclic behaviour to the agent which detects incoming
         * messages.
         */
        addBehaviour(receiveMessageBehaviour);

    }

    @Override
    protected void takeDown() {
        System.out.println("Agent " + getLocalName() + " is terminated");
        size.writeToFile();
        doDelete();
        super.takeDown();
    }

    private class ReceiveMessage extends AbstractReceiveMessage {

        public ReceiveMessage(Agent agent) {
            super(agent);
        }

        @Override
        public void action() {
            myAgent = (SoftwareSystem) myAgent;
            aclmessage = myAgent.receive();
            if (aclmessage != null) {
                getMessageFromACL();
                if (message.contains(EVOLVE_BY)) {
                    String[] messages = message.split(MESSAGE_SPLITTER);
                    try {
                        int evolveBy = Integer.parseInt(messages[1]);
                        myAgent.addBehaviour(new Evolve(myAgent, size, evolveBy));
                        sendMessage(SOURCECODE_AID, RANDOM_REFACTORING + MESSAGE_SPLITTER + evolveBy, REQUEST_MESSAGE_TYPE);
                        //sendMessage(SOURCECODE_AID, DEFACTOR, REQUEST_MESSAGE_TYPE);
                    } catch (NumberFormatException ex) {
                        Logger.getLogger(myjadeinit.actors.SoftwareSystem.ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                switch (message) {
                    case REQUEST_SOFTWARE_SIZE:
                        if (size != null) {
                            sendMessage(DEVELOPER_AID, RETURN_SOFTWARE_SIZE + MESSAGE_SPLITTER + String.valueOf(size.getSoftSize()), DEFAULT_MESSAGE_TYPE);
                        } else {
                            sendMessage(DEVELOPER_AID, RETURN_SOFTWARE_SIZE + MESSAGE_SPLITTER + ERROR_MESSAGE, FAILURE_MESSAGE_TYPE);
                        }
                        break;
                    case EVOLVE:
                        myAgent.addBehaviour(new Evolve(myAgent, size));
                        sendMessage(SOURCECODE_AID, DEFACTOR, DEFAULT_MESSAGE_TYPE);
                        break;
                    case DEGENERATE:
                        myAgent.addBehaviour(new Degenarate(myAgent, size));
                        break;
                    case DIE_MESSAGE:
                        sendMessage(DEVELOPER_AID, DIE_MESSAGE, DEFAULT_MESSAGE_TYPE);
                        sendMessage(USER_AID, DIE_MESSAGE, DEFAULT_MESSAGE_TYPE);
                        sendMessage(MANAGER_AID, DIE_MESSAGE, DEFAULT_MESSAGE_TYPE);
                        myAgent.doSuspend();
                        break;
                }
            }
            aclmessage = null;
        }

    }

}
