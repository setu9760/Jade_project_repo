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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import myjadeinit.actors.Developer;
import myjadeinit.actors.ProjectManager;
import myjadeinit.actors.SoftwareSystem;
import myjadeinit.actors.SourceCode;
import myjadeinit.actors.SystemOwners;
import myjadeinit.actors.User;
import myjadeinit.extras.ChangeRequirement;
import myjadeinit.extras.InitialiseVariable;
import myjadeinit.extras.SourceCodeQuality;
import myjadeinit.extras.SystemSize;

/**
 *
 * @author Desai
 */
public class ReceiveMessage extends CyclicBehaviour {

    /**
     */
    private final int MAX = 10;
    /**
     */
    private final int MIN = 1;
    /**
     */
    private final String EVOLVE = "evolve";
    /**
     */
    private final String EVOLVE_BY = "evolve by";
    /**
     */
    private final String DEGENERATE = "degenerate";
    /**
     */
    private final String REFACTOR = "refactor";
    /**
     */
    private final String REFACTOR_BY = "refactor by";
    /**
     */
    private final String DEFACTOR = "defactor";
    /**
     */
    private final String DEFACTOR_BY = "defactor by";
    /**
     */
    private final String REFACTORING_REQUIRED = "refactoring required";
    /**
     */
    private final String REQUEST_SOFTWARE_SIZE = "request software size";
    /**
     */
    private final String RETURN_SOFTWARE_SIZE = "return software size";
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
    private final String REQUEST_CODE_QUALITY = "request code quality";
    /**
     */
    private final String RETURN_CODE_QUALITY = "return code quality";
    /**
     */
    private final String RANDOM_REFACTORING = "random refactoring";
    /**
     */
    private final String MESSAGE_SPLITTER = ",";
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
    private final String ERROR_MESSAGE = "ERROR!!!!!";
    /**
     */
    private final int DEFAULT_MESSAGE_TYPE = ACLMessage.INFORM;
    /**
     */
    private final int REQUEST_MESSAGE_TYPE = ACLMessage.REQUEST;
    /**
     */
    private final int FAILURE_MESSAGE_TYPE = ACLMessage.FAILURE;
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
    private final String MANAGER_AGENT = "Manager";
    /**
     */
    private final String USER_AGENT = "User";
    /**
     */
    private final AID DEFAULT_AID = new AID("ams", AID.ISLOCALNAME);
    /**
     */
    private final AID DEVELOPER_AID = new AID(DEVELOPER_AGENT, AID.ISLOCALNAME);
    /**
     */
    private final AID SYSTEM_AID = new AID(SYSTEM_AGENT, AID.ISLOCALNAME);
    /**
     */
    private final AID SOURCECODE_AID = new AID(SOURCECODE_AGENT, AID.ISLOCALNAME);
    /**
     */
    private final AID MANAGER_AID = new AID(MANAGER_AGENT, AID.ISLOCALNAME);
    /**
     */
    private final AID USER_AID = new AID(USER_AGENT, AID.ISLOCALNAME);
    /**
     */
    private boolean USER_INIT_DONE = false;

    private ACLMessage aclmessage;
    private String message;
    private SystemSize size;
    private SourceCodeQuality codeQuality;
    private final Random random = new Random();

    /**
     * This is constructor to be used for the SoftwareSystem agent.
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
     * This constructor is to be used for the SourceCode Agent.
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
        // waitThread();
        aclmessage = myAgent.receive();
        if (aclmessage != null) {
            message = aclmessage.getContent().toLowerCase(locale);
            printMessage(aclmessage.getSender(), message);

            if (myAgent instanceof SoftwareSystem) {
                myAgent = (SoftwareSystem) myAgent;
                if (message.contains(EVOLVE_BY)) {
                    String[] messages = message.split(MESSAGE_SPLITTER);
                    try {
                        int evolveBy = Integer.parseInt(messages[1]);
                        myAgent.addBehaviour(new Evolve(myAgent, size, evolveBy));
                        sendMessage(SOURCECODE_AID, RANDOM_REFACTORING + MESSAGE_SPLITTER + evolveBy, REQUEST_MESSAGE_TYPE);
                        //sendMessage(SOURCECODE_AID, DEFACTOR, REQUEST_MESSAGE_TYPE);
                    } catch (NumberFormatException ex) {
                        Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
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

            } else if (myAgent instanceof SourceCode) {
                myAgent = (SourceCode) myAgent;

                if (message.contains(RANDOM_REFACTORING)) {
                    String[] messages = message.split(MESSAGE_SPLITTER);
                    try {
                        int changeSize = Integer.parseInt(messages[1]);
                        int randomRefactoringNumber = RandomizeCodeQialityPolicy(changeSize);
                        if (randomRefactoringNumber > 0) {
                            myAgent.addBehaviour(new Refactor(myAgent, codeQuality, randomRefactoringNumber));
                        } else if (randomRefactoringNumber < 0) {
                            //multiplying by -1 because the returning number will be negative but the constructor must be passed with 
                            //positive number which then will be subtracted from the code quality.
                            myAgent.addBehaviour(new Defactor(myAgent, codeQuality, (-1) * randomRefactoringNumber));
                        }
                    } catch (NumberFormatException ex) {
                        Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                switch (message) {
                    case REFACTOR:
                        myAgent.addBehaviour(new Refactor(myAgent, codeQuality));
                        sendMessage(DEVELOPER_AID, "Refactoring done", DEFAULT_MESSAGE_TYPE);
                        break;
                    case DEFACTOR:
                        myAgent.addBehaviour(new Defactor(myAgent, codeQuality));
                        break;
                    case REQUEST_CODE_QUALITY:
                        if (codeQuality != null) {
                            sendMessage(DEVELOPER_AID, RETURN_CODE_QUALITY + MESSAGE_SPLITTER + String.valueOf(codeQuality.getCodeQuality()), DEFAULT_MESSAGE_TYPE);
                        } else {
                            sendMessage(DEVELOPER_AID, RETURN_CODE_QUALITY + MESSAGE_SPLITTER + ERROR_MESSAGE, FAILURE_MESSAGE_TYPE);
                        }
                        break;
                }

            } else if (myAgent instanceof Developer) {
                //Just casting the agent
                myAgent = (Developer) myAgent;
                if (message.contains(RETURN_SOFTWARE_SIZE)) {
                    String[] messages = message.split(MESSAGE_SPLITTER);
                    try {
                        int systemSize = Integer.parseInt(messages[1]);
                        message = String.valueOf(systemSize);
                        sendMessage(DEVELOPER_AID, REQUEST_REQUIREMENT_CHANGE, REQUEST_MESSAGE_TYPE);
                    } catch (NumberFormatException ex) {
                        Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (message.contains(RETURN_CODE_QUALITY)) {
                    String[] messages = message.split(MESSAGE_SPLITTER);
                    try {
                        int CodeQuality = Integer.parseInt(messages[1]);
                        message = String.valueOf(CodeQuality);

                        //TODO: do something to apply refactoring or defactoring in each loop
                    } catch (NumberFormatException ex) {
                        Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
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
            } else if (myAgent instanceof User) {
                myAgent = (User) myAgent;
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
                            USER_INIT_DONE = true;
                        }
                        // myAgent.addBehaviour(new ContinuousEvolvution(myAgent));
                        break;
                    case DIE_MESSAGE:
                        myAgent.doSuspend();
                        break;
                }

            } else if (myAgent instanceof SystemOwners) {
                myAgent = (SystemOwners) myAgent;
                //TODO
                //Do something when th agent System owner receives the message
            } else if (myAgent instanceof ProjectManager) {
                myAgent = (ProjectManager) myAgent;
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
                        myAgent.doDelete();
                        break;
                }

            }

            // last line of the if block to release the aclmessage object from memory and reuse it again as this class extends CyclicBehaviour.
            aclmessage = null;
        }
    }

    private void printMessage(AID sender, String message) {

        System.out.println(myAgent.getLocalName() + " received a message from " + sender.getLocalName());
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
     * <p>
     * This method makes the current thread sleep for 1.5 seconds, this method
     * is simply used to see the message interaction in real time. in the
     * beginning of each cyclic behaviour receive message this method is invoked
     * making the process slow and observable.</p>
     * <p>
     * This method does not serve any processing purpose in term of this
     * project, it can simply be removed if not required</p>
     */
    private void waitThread() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * <p>
     * This method randomises the acceptance of the change requirement. This
     * method works on probability of change being accepted by the developer and
     * the manager.</p>
     * <p>
     * The idea is that the smaller the change there is high probability of it
     * being accepted and implemented by the programmers and vice versa if the
     * change is larger. The change requirement size will always very from 1 to
     * 100 inclusive and will always be randomised which makes this process more
     * dynamic.</p>
     *
     * @return 0 for rejecting the change and 1 for accepting it.
     */
    private int ChangeAcceptancePolicy(int changeRequestSize) {

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

    /**
     * This method is a policy which decides the change in code quality.
     * Every-time the requirement change is accepted and applied the software
     * size increases by an integer, this integer is then passed to this method
     * to determine the change in code quality.
     * <p>
     * The change in code quality is normally 10th of the change size and it is
     * by default 1 if the change size is between 1 and 9. This method aso
     * randomises if the the change in code quality will be positive or
     * negative.</p>
     * <p>
     * If the return value is negative then Source code will be refactor
     * resulting in increased code quality but if it is negative value than the
     * code quality will be decreased.</p>
     *
     * @param changeSize The size of change requirement that was applied to the
     * software size.
     * @return The number by which the code quality will be affected.
     */
    private int RandomizeCodeQialityPolicy(int changeSize) {
        int returnNo = 1;

        if (isInBetween(changeSize, 10, 100)) {
            returnNo = changeSize / 10;
        } else if (isInBetween(changeSize, 1, 9)) {
            returnNo = 1;
        }
        int rand = random.nextInt((1 - 0) + 1) + 0;
        switch (rand) {
            case 1:
                //do nothing here as if random integer is 1 then returnNo will be positive.
                break;
            case 0:
                //Make the returnNo negative if the random integer is 0.
                returnNo = 0 - returnNo;
                break;
        }
        return returnNo;
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
    public boolean isInBetween(int num, int lowerBoundary, int higherBoundary) {
        return (num >= lowerBoundary && num <= higherBoundary);
    }

    /**
     * <p>
     * This is a private inner class to constantly send evolve message from the
     * user agent, by this behaviour the user agent will keep sending continuous
     * evolution request to the project manager every 10 seconds.</p>
     * <p>
     * Purpose of this class is to make the model dynamic and present it as
     * continuous circle</p>
     */
    private class ContinuousEvolvution extends Behaviour {

        private int numOfCycles = InitialiseVariable.numOfCycles;

        public ContinuousEvolvution(Agent agent) {
            super(agent);
        }

        @Override
        public void action() {

            try {
                Thread.sleep(InitialiseVariable.timeInterval);
                sendMessage(new AID(DEVELOPER_AGENT, AID.ISLOCALNAME), EVOLVE, REQUEST_MESSAGE_TYPE);
                numOfCycles--;
            } catch (InterruptedException ex) {
                Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public boolean done() {
            return numOfCycles == 0;
        }
    }
}
