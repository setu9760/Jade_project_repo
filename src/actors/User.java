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
package actors;

import abstracts.AbstractActor;
import abstracts.AbstractMessageReceiver;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.FinalAlert;
import utils.InitialiseVariable;

/**
 *
 * @author Desai
 */
public class User extends AbstractActor {

    //private ReceiveMessage receiveMessageBehaviour;

    @Override
    protected void setup() {
        welcomMessage();
        receiveMessageBehaviour = new ReceiveMessage(this);
        addBehaviour(receiveMessageBehaviour);
    }

    private class ReceiveMessage extends AbstractMessageReceiver {

        public ReceiveMessage(Agent agent) {
            super(agent);
        }

        @Override
        public void action() {
            myAgent = (User) myAgent;
            aclmessage = myAgent.receive();
            if (aclmessage != null) {
                getMessageFromACL();
                switch (message) {
                    case DEFAULT_HELLO:
                        /**
                         * This case statement adds continuous change request
                         * behaviour to the user agent /this will only be done
                         * once in the complete software evolution process
                         * model.
                         */
                        if (!USER_INIT_DONE) {
                            myAgent.addBehaviour(new ContinuousEvolvution(myAgent));
                            myAgent.addBehaviour(new ContinuousChageRequest(myAgent));
                            USER_INIT_DONE = true;
                        }
                        break;
                    case SUSPEND_MESSAGE:
                        doSuspend();
                        break;
                }

            }
            aclmessage = null;
        }

        private class ContinuousEvolvution extends Behaviour {

            private int numOfCycles = InitialiseVariable.numOfCycles;

            public ContinuousEvolvution(Agent agent) {
                super(agent);
            }

            @Override
            public void action() {

                try {
                    Thread.sleep(InitialiseVariable.timeInterval);
                    sendMessage(MANAGER_AID, EVOLVE, REQUEST_MESSAGE_TYPE);
                    numOfCycles--;

                } catch (InterruptedException ex) {
                    Logger.getLogger(actors.User.ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public boolean done() {
                return numOfCycles == 0;
            }
        }

        private class ContinuousChageRequest extends Behaviour {

            private int numOfCycles = InitialiseVariable.numOfCycles;

            public ContinuousChageRequest(Agent agent) {
                super(agent);
            }

            @Override
            public void action() {
                try {
                    Thread.sleep(InitialiseVariable.timeInterval);
                    sendMessage(MANAGER_AID, REQUEST_REQUIREMENT_CHANGE, REQUEST_MESSAGE_TYPE);
                    numOfCycles--;

                } catch (InterruptedException ex) {
                    Logger.getLogger(actors.User.ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public boolean done() {
                if (numOfCycles == 0) {
                    sendMessage(SYSTEM_AID, SUSPEND_MESSAGE, DEFAULT_MESSAGE_TYPE);
                    new Thread(new FinalAlert()).start();
                }
                return numOfCycles == 0;
            }

        }
    }

}
