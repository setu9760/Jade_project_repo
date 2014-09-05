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
import interfaces.ChangeAcceptancePolicyFetcher;
import jade.core.Agent;
import utils.ChangeRequirement;

/**
 *
 * @author Desai
 */
public class Developer extends AbstractActor {

    ReceiveMessage receiveMessageBehaviour;

    @Override
    protected void setup() {
        welcomMessage();
        receiveMessageBehaviour = new ReceiveMessage(this);
        addBehaviour(receiveMessageBehaviour);

    }

    private class ReceiveMessage extends AbstractMessageReceiver implements ChangeAcceptancePolicyFetcher {

        public ReceiveMessage(Agent agent) {
            super(agent);
        }

        @Override
        public void action() {
            myAgent = (Developer) myAgent;
            aclmessage = myAgent.receive();
            if (aclmessage != null) {
                getMessageFromACL();

                switch (message) {
                    case EVOLVE:
                        sendMessage(SYSTEM_AID, EVOLVE, DEFAULT_MESSAGE_TYPE);
                        break;
                    case REFACTOR:
                        sendMessage(SOURCECODE_AID, REFACTOR, DEFAULT_MESSAGE_TYPE);
                        break;
                    case REQUEST_REQUIREMENT_CHANGE:
                        ChangeRequirement change = new ChangeRequirement();
                        switch (ChangeAcceptancePolicy(change.getChangeRequirementSize())) {
                            case 1:
                                System.out.println("change size is :" + change.getChangeRequirementSize());
                                System.out.println("change is accepted");
                                sendMessage(SYSTEM_AID, EVOLVE_BY + "," + change.getChangeRequirementSize(), REQUEST_MESSAGE_TYPE);
                                sendMessage(MANAGER_AID, ACCEPT_REQUIREMENT_CHANGE, ACCEPT_MESSAGE_TYPE);
                                break;
                            case 0:
                                System.out.println("change size was :" + change.getChangeRequirementSize());
                                System.out.println("change is rejected");
                                sendMessage(MANAGER_AID, DECLINE_REQUIREMENT_CHANGE, REJECT_MESSAGE_TYPE);
                                break;
                        }

                        break;
                    case "":
                        //TODO:
                        break;
                    case SUSPEND_MESSAGE:
                        doSuspend();
                        break;
                }
            }
            aclmessage = null;
        }

        /**
         * <p>
         * This method randomises the acceptance of the change requirement. This
         * method works on probability of change being accepted by the developer
         * and the manager.</p>
         * <p>
         * The idea is that the smaller the change there is high probability of
         * it being accepted and implemented by the programmers and vice versa
         * if the change is larger. The change requirement size will always very
         * from 1 to 100 inclusive and will always be randomised which makes
         * this process more dynamic.</p>
         *
         * @return 0 for rejecting the change and 1 for accepting it.
         */
        @Override
        public int ChangeAcceptancePolicy(int changeRequestSize) {
            int rand;
            ///////////////////
            if (isInBetween(changeRequestSize, 1, 20)) {
                //If change size is between 1 and 20 there are
                // 80% chances of it being accepted randomly.
                rand = random.nextInt((MAX - MIN) + 1) + MIN;

                return (isInBetween(rand, 1, 8)) ? 1 : 0;

            } else if (isInBetween(changeRequestSize, 21, 40)) {
                //If change size is between 21 and 40 there are
                //60% chances of it being accepted randomly.
                rand = random.nextInt((MAX - MIN) + 1) + MIN;

                return (isInBetween(rand, 1, 6)) ? 1 : 0;

            } else if (isInBetween(changeRequestSize, 41, 60)) {
                //If change size is between 41 and 60 there are
                //50% chances of it being accepted randomly.
                rand = random.nextInt((MAX - MIN) + 1) + MIN;

                return (isInBetween(rand, 1, 5)) ? 1 : 0;
            } else if (isInBetween(changeRequestSize, 61, 80)) {
                //If change size is between 61 and 80 there are 
                //only 40% chances of it being accepted randomly.
                rand = random.nextInt((MAX - MIN) + 1) + MIN;

                return (isInBetween(rand, 1, 4)) ? 1 : 0;

            } else if (isInBetween(changeRequestSize, 81, 100)) {
                //If change size is between 81 and 100 it is considered
                //exponantial and there fore there are only 20% chances 
                //of it being accepted randomly.
                rand = random.nextInt((MAX - MIN) + 1) + MIN;

                return (isInBetween(rand, 1, 2)) ? 1 : 0;
            }

            return 0;
        }

    }

}
