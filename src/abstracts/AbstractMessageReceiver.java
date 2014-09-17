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

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is an abstract class which extends CyclicBehaviour class form the jade
 * library. This class implements the members and methods which are necessary
 * for all or some behaviours in this model. All the agent's receiveMessage
 * behaviour must extend this class. This method has an abstract method action()
 * which must be implemented by the developer. The implementation would
 * generally consist of action to be taken when an agent receives message.
 * <p>
 * This class consists of many final Strings which the agents might receive as
 * message but for future implementation more strings could be added to this
 * class. This class also has implementation to many other useful methods</p>
 * <p>
 * <i>All the agent's receiveMessage behaviour must extend this class otherwise
 * the developer will not be able to take advantage of methods from this
 * class.</i></p>
 *
 * @author Desai
 */
public abstract class AbstractMessageReceiver extends CyclicBehaviour {

    /**
     */
    protected final int MAX = 10;
    /**
     */
    protected final int MIN = 1;
    /**
     */
    protected final String EVOLVE = "evolve";
    /**
     */
    protected final String EVOLVE_BY = "evolve by";
    /**
     */
    protected final String DEGENERATE = "degenerate";
    /**
     */
    protected final String REFACTOR = "refactor";
    /**
     */
    protected final String REFACTOR_BY = "refactor by";
    /**
     */
    protected final String DEFACTOR = "defactor";
    /**
     */
    protected final String DEFACTOR_BY = "defactor by";
    /**
     */
    protected final String REFACTORING_REQUIRED = "refactoring required";
    /**
     */
    protected final String REQUEST_SOFTWARE_SIZE = "request software size";
    /**
     */
    protected final String RETURN_SOFTWARE_SIZE = "return software size";
    /**
     */
    protected final String REQUEST_REQUIREMENT_CHANGE = "change requirement";
    /**
     */
    protected final String ACCEPT_REQUIREMENT_CHANGE = "accept requirement change";
    /**
     */
    protected final String DECLINE_REQUIREMENT_CHANGE = "decline requirement change";
    /**
     */
    protected final String REQUEST_CODE_QUALITY = "request code quality";
    /**
     */
    protected final String RETURN_CODE_QUALITY = "return code quality";
    /**
     */
    protected final String RANDOM_REFACTORING = "random refactoring";
    /**
     */
    protected final String MESSAGE_SPLITTER = ",";
    /**
     */
    protected final String DEFAULT_HELLO = "hello";
    /**
     */
    protected final String SUSPEND_MESSAGE = "suspend";
    /**
     */
    protected final String DEFAULT_MESSAGE = "This is a default string for the message, null value was passed in the message content";
    /**
     */
    protected final String ERROR_MESSAGE = "ERROR!!!!!";
    /**
     */
    protected final int DEFAULT_MESSAGE_TYPE = ACLMessage.INFORM;
    /**
     */
    protected final int REQUEST_MESSAGE_TYPE = ACLMessage.REQUEST;
    /**
     */
    protected final int REJECT_MESSAGE_TYPE = ACLMessage.REJECT_PROPOSAL;
    /**
     */
    protected final int ACCEPT_MESSAGE_TYPE = ACLMessage.ACCEPT_PROPOSAL;
    /**
     */
    protected final int FAILURE_MESSAGE_TYPE = ACLMessage.FAILURE;
    /**
     */
    protected final Locale locale = Locale.ENGLISH;
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
    private final String MANAGER_AGENT = "Manager";
    /**
     */
    private final String USER_AGENT = "User";
    /**
     */
    protected final AID DEFAULT_AID = new AID("ams", AID.ISLOCALNAME);
    /**
     */
    protected final AID DEVELOPER_AID = new AID(DEVELOPER_AGENT, AID.ISLOCALNAME);
    /**
     */
    protected final AID SYSTEM_AID = new AID(SYSTEM_AGENT, AID.ISLOCALNAME);
    /**
     */
    protected final AID SOURCECODE_AID = new AID(SOURCECODE_AGENT, AID.ISLOCALNAME);
    /**
     */
    protected final AID MANAGER_AID = new AID(MANAGER_AGENT, AID.ISLOCALNAME);
    /**
     */
    protected final AID USER_AID = new AID(USER_AGENT, AID.ISLOCALNAME);
    /**
     */
    protected boolean USER_INIT_DONE = false;
    /**
     */
    protected final Random random = new Random();

    protected ACLMessage aclmessage;
    protected String message;

    /**
     * Default constructor which requires the corresponding agent to be passed
     * as an argument.
     *
     * @param agent The agent to which this behaviour belongs.
     */
    public AbstractMessageReceiver(Agent agent) {
        super(agent);
    }

    /**
     * Abstract method for which the implementation is left to the developer.
     * Generally would consist of action() to be take upon the agent receiving a
     * message.
     */
    @Override
    public abstract void action();

    /**
     * This method is developed to simply print the message in the consol or
     * terminal during runtime.
     *
     * @param sender Sender of the message
     * @param message String content of the message
     */
    protected final void printMessage(AID sender, String message) {

        System.out.println(myAgent.getLocalName() + " received a message from " + sender.getLocalName());
        System.out.println("Message: " + message);

    }

    /**
     * This method converts ACLMessage to a String message.
     */
    protected final void getMessageFromACL() {
        message = aclmessage.getContent().toLowerCase(locale);
        printMessage(aclmessage.getSender(), message);
    }

    /**
     * @return Checks the aclmessage for null if not it returns the String
     * representation of message.
     */
    public final String returnContentMessage() {
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
    protected final void sendMessage(AID receiver, String messageContent, int messageType) {

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
     * <p>
     * This method is used to send a reply to unknown sender if any of the agent
     * receives message from unidentified agent.</p>
     * <p>
     * This method does not serve any processing purpose in term of this
     * project, it can simply be removed if not required</p>
     *
     * @param message
     */
    protected final void defaultReply(ACLMessage message) {

        ACLMessage reply = message.createReply();
        reply.setPerformative(DEFAULT_MESSAGE_TYPE);
        reply.setContent("Message received from unknow sender");
        myAgent.send(reply);
    }

    /**
     * <p>
     * This method makes the current thread sleep for specified number of
     * seconds, this method is simply used to see the message interaction in
     * real time.</p>
     * <p>
     * This method does not serve any processing purpose in term of this
     * project, it can simply be removed if not required</p>
     */
    protected final void waitThread() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(AbstractMessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * <p>
     * This is a method to check if a number is in between two numbers. This is
     * mainly used in this class to test change request size in if else
     * condition.</p>
     *
     * @param num The number to be tested
     * @param lowerBoundary the lower boundary (inclusive)
     * @param higherBoundary the higher boundary (inclusive)
     * @return true if with in the lower and higher boundary false otherwise.
     */
    protected final boolean isInBetween(int num, int lowerBoundary, int higherBoundary) {
        return (num >= lowerBoundary && num <= higherBoundary);
    }
}
