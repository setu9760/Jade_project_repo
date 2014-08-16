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

import interfaces.ChangeAcceptancePolicyFetcher;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import myjadeinit.behaviours.AbstractReceiveMessage;
import myjadeinit.extras.ChangeRequirement;
import myjadeinit.extras.InitialiseVariable;

/**
 *
 * @author Desai
 */
public class Developer extends Agent {

    /**
     */
    private final String REQUEST_SOFTWARE_SIZE = "request software size";
    /**
     */
    private final String REQUEST_CODE_QUALITY = "request code quality";
    /**
     */
    private final int DEFAULT_MESSAGE_TYPE = ACLMessage.INFORM;
    /**
     */
    private final AID DEFAULT_AID = new AID("ams", AID.ISLOCALNAME);
    /**
     */
    private final String DEFAULT_MESSAGE = "This is a default string for the message, null value was passed in the message content";

    private int numOfCycles = InitialiseVariable.numOfCycles;

    ReceiveMessage receiveMessageBehaviour;

    ACLMessage aclmessage;

    @Override
    protected void setup() {
        System.out.println("Hello World!!!! \n Agent: " + getLocalName() + " is created.");
        receiveMessageBehaviour = new ReceiveMessage(this);
        addBehaviour(receiveMessageBehaviour);
        Thread thread;
        thread = new Thread() {

            @Override
            public void run() {
                while (numOfCycles != 0) {
                    try {
                        Thread.sleep(InitialiseVariable.timeInterval);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Developer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    sendMessage(new AID("System", AID.ISLOCALNAME), REQUEST_SOFTWARE_SIZE, ACLMessage.REQUEST);
                    //sendMessage(new AID("SourceCode", AID.ISLOCALNAME), RE, ACLMessage.REQUEST);
                    numOfCycles--;
                }
            }
        };
        // Setting it as daemon thread as it will keep running in background 
        //to execute perticular operation every 5000 milliseconds.
        thread.setDaemon(true);
        thread.start();

    }

    @Override
    protected void takeDown() {
        System.out.println("Agent " + getLocalName() + " is terminated");
        doDelete();
        super.takeDown();
    }

    private class ReceiveMessage extends AbstractReceiveMessage implements ChangeAcceptancePolicyFetcher {

        public ReceiveMessage(Agent agent) {
            super(agent);
        }

        @Override
        public void action() {
            myAgent = (Developer) myAgent;
            aclmessage = myAgent.receive();
            if (aclmessage != null) {
                getMessageFromACL();
                if (message.contains(RETURN_SOFTWARE_SIZE)) {
                    String[] messages = message.split(MESSAGE_SPLITTER);
                    try {
                        int systemSize = Integer.parseInt(messages[1]);
                        message = String.valueOf(systemSize);
                        sendMessage(DEVELOPER_AID, REQUEST_REQUIREMENT_CHANGE, REQUEST_MESSAGE_TYPE);
                    } catch (NumberFormatException ex) {
                        Logger.getLogger(myjadeinit.behaviours.ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (message.contains(RETURN_CODE_QUALITY)) {
                    String[] messages = message.split(MESSAGE_SPLITTER);
                    try {
                        int CodeQuality = Integer.parseInt(messages[1]);
                        message = String.valueOf(CodeQuality);

                        //TODO: do something to apply refactoring or defactoring in each loop
                    } catch (NumberFormatException ex) {
                        Logger.getLogger(myjadeinit.behaviours.ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
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
                                    break;
                                case 0:
                                    System.out.println("change size was :" + change.getChangeRequirementSize());
                                    System.out.println("change is rejected");
                                    sendMessage(MANAGER_AID, DECLINE_REQUIREMENT_CHANGE, ACLMessage.REFUSE);
                                    break;
                            }

                            break;
                        case "":
                            //TODO:
                            break;
                        case DIE_MESSAGE:
                            myAgent.doDelete();
                            break;
                    }
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

    /**
     * This message is used in this class to send automated messages to other
     * agents in the container.
     *
     * @param receiver the receiver agent, if null message is sent to the ams.
     * @param messageContent content of the message, if null default string is
     * sent
     * @param messageType message type or FIPA performative identifier, if null
     * then ACLMessage.INFORM performative is applied.
     *
     */
    private void sendMessage(AID receiver, String messageContent, int messageType) {

        receiver = (AID) isNull(receiver, 1);
        messageContent = (String) isNull(messageContent, 2);
        messageType = (int) isNull(messageType, 3);

        aclmessage = new ACLMessage(messageType);
        aclmessage.setContent(messageContent);
        aclmessage.addReceiver(receiver);
        send(aclmessage);

    }

    /**
     * This is a method to check if any of the parameter in #sendMessage() is
     * null and if it is then this method will assign default values to those
     * object.
     *
     * @param object object that needs to be checked against null.
     * @param paramNumber the parameter that needs to be tested takes value 1, 2
     * or 3.
     * @return return default valued object in the object was null or the same
     * object;
     */
    private Object isNull(Object object, int paramNumber) {
        if (object == null) {
            switch (paramNumber) {
                case 1:
                    object = (AID) DEFAULT_AID;
                    break;
                case 2:
                    object = (String) DEFAULT_MESSAGE;
                    break;
                case 3:
                    object = (int) DEFAULT_MESSAGE_TYPE;
                    break;
            }
        }
        return object;
    }

}
