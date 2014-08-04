/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.actors;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import myjadeinit.behaviours.ReceiveMessage;

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
                while (true) {
                    try {
                        Thread.sleep(15000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Developer.class.getName()).log(Level.SEVERE, null, ex);
                    }
//                    sendMessage(new AID("System", AID.ISLOCALNAME), REQUEST_SOFTWARE_SIZE, ACLMessage.REQUEST);
//                    sendMessage(new AID("SourceCode", AID.ISLOCALNAME), REQUEST_CODE_QUALITY, ACLMessage.REQUEST);

                }
            }
        };
        // Setting it as daemon thread as it will keep running in background 
//          to execute perticular operation every 5000 milliseconds.
        thread.setDaemon(true);
        thread.start();

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
