/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import myjadeinit.actors.*;
import myjadeinit.extras.SourceCodeQuality;
import myjadeinit.extras.SystemSize;

/**
 *
 * @author Desai
 */
public class ReceiveMessage extends CyclicBehaviour {

    /* */
    public static final String EVOLVE = "evolve";
    /* */
    public static final String DEGENERATE = "degenerate";
    /* */
    private static final String REFACTOR = "refactor";
    /* */
    private static final String REFACTORING_REQUIRED = "Refactoring required";
    /* */
    private final AID DEFAULT_AID = new AID("ams", AID.ISLOCALNAME);
    /* */
    private final String DEFAULT_MESSAGE = "This is a default string for the message, null value was passed in the message content";
    /* */
    private final int DEFAULT_MESSAGE_TYPE = ACLMessage.INFORM;

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
        aclmessage = myAgent.receive();
        if (aclmessage != null) {
            message = aclmessage.getContent();
            printMessage(aclmessage.getSender(), message);
            if (myAgent instanceof SoftwareSystem) {
                switch (message) {
                    case EVOLVE:
                        myAgent.addBehaviour(new Evolve(myAgent, 1, size));
                        myAgent.addBehaviour(new Defactor(myAgent, SourceCode.codeQuality));
                        sendMessage(new AID("Developer", AID.ISLOCALNAME), REFACTORING_REQUIRED, ACLMessage.INFORM);
                        break;
                    case DEGENERATE:
                        myAgent.addBehaviour(new Degenarate(myAgent, 1, size));
                        break;

                }
            } else if (myAgent instanceof SourceCode) {
                if (message.equals(REFACTOR)) {
                    myAgent.addBehaviour(new Refactor(myAgent, codeQuality));
                }
            } else if (myAgent instanceof Developer) {
                switch (message) {
                    case EVOLVE:
                        sendMessage(new AID("SoftwareSystem", AID.ISLOCALNAME), EVOLVE, ACLMessage.INFORM);
                        break;
                    case REFACTORING_REQUIRED:
                        sendMessage(new AID("SourceCode", AID.ISLOCALNAME), REFACTOR, ACLMessage.INFORM);
                        break;
                }
            }

            // last line of the if block to release the aclmessage object from memory and reuse it again as this class extends CyclicBehaviour.
            aclmessage = null;
        }

        block();
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
     * then ACLMessage#INFORM performative is applied.
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
     * @param contentNumber the parameter that needs to be tested takes value 1,
     * 2 or 3.
     * @return return default valued object in the object was null or the same
     * object;
     */
    private Object isNull(Object object, int contentNumber) {
        if (object == null) {
            switch (contentNumber) {
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
