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
import interfaces.RandomizeCodeQualityPolicyFetcher;
import jade.core.Agent;
import java.util.logging.Level;
import java.util.logging.Logger;
import abstracts.AbstractMessageReceiver;
import behaviours.Defactor;
import behaviours.Refactor;
import utils.InitialiseVariable;
import utils.SourceCodeQuality;

/**
 *
 * @author Desai
 */
public class SourceCode extends AbstractActor {

    private final SourceCodeQuality codeQuality;
    private ReceiveMessage receiveMesageBehaviour;

    public SourceCode() {
        this.codeQuality = new SourceCodeQuality(InitialiseVariable.CodeQuality);
    }

    @Override
    protected void setup() {
        welcomMessage();
        receiveMesageBehaviour = new ReceiveMessage(this);
        addBehaviour(receiveMesageBehaviour);
    }

    @Override
    protected void takeDown() {
        codeQuality.writeToFile();
        doDelete();
        super.takeDown();
    }

    private class ReceiveMessage extends AbstractMessageReceiver implements RandomizeCodeQualityPolicyFetcher {

        public ReceiveMessage(Agent agent) {
            super(agent);
        }

        @Override
        public void action() {
            myAgent = (SourceCode) myAgent;
            aclmessage = myAgent.receive();
            if (aclmessage != null) {
                getMessageFromACL();
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
            }
            aclmessage = null;
        }

        /**
         * This method is a policy which decides the change in code quality.
         * Every-time the requirement change is accepted and applied the
         * software size increases by an integer, this integer is then passed to
         * this method to determine the change in code quality.
         * <p>
         * The change in code quality is normally 10th of the change size and it
         * is by default 1 if the change size is between 1 and 9. This method
         * aso randomises if the the change in code quality will be positive or
         * negative.</p>
         * <p>
         * If the return value is negative then Source code will be refactor
         * resulting in increased code quality but if it is negative value than
         * the code quality will be decreased.</p>
         *
         * @param changeSize The size of change requirement that was applied to
         * the software size.
         * @return The number by which the code quality will be affected.
         */
        @Override
        public int RandomizeCodeQialityPolicy(int changeSize) {
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
    }
}
