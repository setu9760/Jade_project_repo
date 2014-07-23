/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package myjadeinit.behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import myjadeinit.actors.Developer;
import myjadeinit.actors.ProjectManager;
import myjadeinit.actors.SoftwareSystem;
import myjadeinit.actors.SourceCode;
import myjadeinit.actors.SystemOwners;
import myjadeinit.actors.User;
import myjadeinit.extras.SourceCodeQuality;
import myjadeinit.extras.SystemSize;

/**
 *
 * @author Desai
 */
public class ReceiveMessage extends CyclicBehaviour {

    /**
     */
    public final String EVOLVE = "evolve";
    /**
     */
    public final String DEGENERATE = "degenerate";
    /**
     */
    private final String REFACTOR = "refactor";
    /**
     */
    private final String DEFACTOR = "defactor";
    /**
     */
    private final String REFACTORING_REQUIRED = "refactoring required";
    /**
     */
    private final String REQUEST_REQUIREMENT_CHANGE = "change requirement";
    /**
     */
    private final String ACCEPT_REQUIREMENT_CHANGE = "accept requirement change";
    /**
     */
    private final String DECLINE_REQUIREMENT_CHANGE = "decline requirement change";
    /**
     */
    private final String DEFAULT_HELLO = "hello";
    /**
     */
    private final String DIE_MESSAGE = "die";
    /**
     */
    private final String DEFAULT_MESSAGE = "This is a default string for the message, null value was passed in the message content";
    /**
     */
    private final AID DEFAULT_AID = new AID("ams", AID.ISLOCALNAME);

    /**
     */
    private final int DEFAULT_MESSAGE_TYPE = ACLMessage.INFORM;
    /**
     */
    private final int REQUEST_MESSAGE_TYPE = ACLMessage.REQUEST;
    /**
     */
    private final Locale locale = Locale.ENGLISH;
    /**
     */
    private final String DEVELOPER_AGENT = "Developer";
    /**
     */
    private final String SYSTEM_AGENT = "System";
    /**
     */
    private final String SOURCECODE_AGENT = "SourceCode";
    /**
     */
    private final String PROJECT_MANAGER_AGENT = "Manager";
    /**
     */
    private final String USER_AGENT = "User";
    /**
     */
    private boolean USER_INIT_DONE = false;

    private ACLMessage aclmessage;
    private String message;
    private SystemSize size;
    private SourceCodeQuality codeQuality;

    /**
     * This is constructor to be used in the SoftwareSystem agent.
     *
     * @param agent: my agent
     * @param size: Software system size
     * @see SoftwareSystem
     */
    public ReceiveMessage(Agent agent, SystemSize size) {
        super(agent);
        this.size = size;
    }

    /**
     * This constructor is to be used in the SourceCode Agent.
     *
     * @param agent: my agent
     * @param codeQuality: Code quality of Software System
     */
    public ReceiveMessage(Agent agent, SourceCodeQuality codeQuality) {
        super(agent);
        this.codeQuality = codeQuality;
    }

    /**
     * This is a default constructor.
     *
     * @param agent: my agent
     */
    public ReceiveMessage(Agent agent) {
        super(agent);
    }

    @Override
    public void action() {
        waitThread();
        aclmessage = myAgent.receive();
        if (aclmessage != null) {
            message = aclmessage.getContent().toLowerCase(locale);
            printMessage(aclmessage.getSender(), message);

            if (myAgent instanceof SoftwareSystem) {
                switch (message) {
                    case EVOLVE:
                        myAgent.addBehaviour(new Evolve(myAgent, size));
                        sendMessage(new AID(SOURCECODE_AGENT, AID.ISLOCALNAME), DEFACTOR, DEFAULT_MESSAGE_TYPE);
                        sendMessage(new AID(DEVELOPER_AGENT, AID.ISLOCALNAME), REFACTORING_REQUIRED, DEFAULT_MESSAGE_TYPE);
                        break;
                    case DEGENERATE:
                        myAgent.addBehaviour(new Degenarate(myAgent, size));
                        break;
                    case DIE_MESSAGE:
                        sendMessage(new AID(DEVELOPER_AGENT, AID.ISLOCALNAME), DIE_MESSAGE, DEFAULT_MESSAGE_TYPE);
                        sendMessage(new AID(USER_AGENT, AID.ISLOCALNAME), DIE_MESSAGE, DEFAULT_MESSAGE_TYPE);
                        sendMessage(new AID(PROJECT_MANAGER_AGENT, AID.ISLOCALNAME), DIE_MESSAGE, DEFAULT_MESSAGE_TYPE);
                        //sendMessage(new AID(DEVELOPER_AGENT, AID.ISLOCALNAME), DIE_MESSAGE, DEFAULT_MESSAGE_TYPE);
                        myAgent.doSuspend();
                        break;

                }

            } else if (myAgent instanceof SourceCode) {
                switch (message) {
                    case REFACTOR:
                        myAgent.addBehaviour(new Refactor(myAgent, codeQuality));
                        sendMessage(new AID(DEVELOPER_AGENT, AID.ISLOCALNAME), "Refactoring done", DEFAULT_MESSAGE_TYPE);
                        break;
                    case DEFACTOR:
                        myAgent.addBehaviour(new Defactor(myAgent, codeQuality));
                        break;

                }

            } else if (myAgent instanceof Developer) {
                //Just casting the agent
                myAgent = (Developer) myAgent;
                switch (message) {
                    case EVOLVE:
                        sendMessage(new AID(SYSTEM_AGENT, AID.ISLOCALNAME), EVOLVE, DEFAULT_MESSAGE_TYPE);
                        break;
                    case REFACTOR:
                        sendMessage(new AID(SOURCECODE_AGENT, AID.ISLOCALNAME), REFACTOR, DEFAULT_MESSAGE_TYPE);
                        break;
                    case REQUEST_REQUIREMENT_CHANGE:
                        //TODO:
                        break;
                    case DIE_MESSAGE:
                        myAgent.doSuspend();
                        break;

                }
            } else if (myAgent instanceof User) {
                //TODO
                switch (message) {
                    case DEFAULT_HELLO:
                        /**
                         * This case statement adds random change request
                         * behaviour to the user agent /this will only be done
                         * once in the complete software evolution process
                         * model. Refer to {}
                         */
                        if (!USER_INIT_DONE) {
                            myAgent.addBehaviour(new RandomChangeRequest(myAgent));
                            USER_INIT_DONE = true;
                        }

                        myAgent.addBehaviour(new RandomChangeRequest(myAgent));
                        break;
                    case DIE_MESSAGE:
                        myAgent.doSuspend();
                        break;

                }

            } else if (myAgent instanceof SystemOwners) {
                // TODO:

            } else if (myAgent instanceof ProjectManager) {
                switch (message) {
                    case EVOLVE:
                        sendMessage(new AID(DEVELOPER_AGENT, AID.ISLOCALNAME), EVOLVE, DEFAULT_MESSAGE_TYPE);
                        break;
                    case REQUEST_REQUIREMENT_CHANGE:
                        sendMessage(new AID(DEVELOPER_AGENT, AID.ISLOCALNAME), REQUEST_REQUIREMENT_CHANGE, REQUEST_MESSAGE_TYPE);
                        break;
                    case ACCEPT_REQUIREMENT_CHANGE:
                        //TODO:
                        break;
                    case DECLINE_REQUIREMENT_CHANGE:
                        //TODO:
                        break;
                    case DIE_MESSAGE:
                        myAgent.doSuspend();
                        break;
                }

            }

            // last line of the if block to release the aclmessage object from memory and reuse it again as this class extends CyclicBehaviour.
            aclmessage = null;
        }
    }

    private void printMessage(AID sender, String message) {

        System.out.println(myAgent.getName() + " received a message from " + sender.getName());
        System.out.println("Message: " + message);

    }

    public String returnContentMessage() {
        if (aclmessage != null) {
            return message;

        }
        return "";
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
        myAgent.send(aclmessage);

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

    /**
     * This method is used to send a reply to unknown sender if any of the agent
     * receives message from unidentified agent.
     *
     * @param message
     */
    private void defaultReply(ACLMessage message) {

        ACLMessage reply = message.createReply();
        reply.setPerformative(DEFAULT_MESSAGE_TYPE);
        reply.setContent("Message received from unknow sender");
        myAgent.send(reply);
    }

    /**
     *
     */
    private void waitThread() {

        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This is a private inner class to apply a constantly active behaviour to
     * the user agent, by this behaviour the user agent will keep sending
     * requirement change request to the project manager every 30 seconds.
     */
    private class RandomChangeRequest extends Behaviour {

        public RandomChangeRequest(Agent agent) {
            super(agent);
        }

        @Override
        public void action() {
            sendMessage(new AID(DEVELOPER_AGENT, AID.ISLOCALNAME), EVOLVE, REQUEST_MESSAGE_TYPE);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public boolean done() {
            return false;
        }

    }
}
