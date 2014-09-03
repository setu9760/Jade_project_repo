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
package behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import utils.SourceCodeQuality;

/**
 *
 * @author Desai
 */
public class Defactor extends Behaviour {

    /**
     */
    private final SourceCodeQuality codeQuality;
    private boolean done = false;
    private int DefactorBy = 0;

    /**
     *
     * @param agent
     * @param codeQuality
     */
    public Defactor(Agent agent, SourceCodeQuality codeQuality) {
        super(agent);
        this.codeQuality = codeQuality;
    }

    /**
     *
     * @param agent
     * @param codeQuality
     * @param DefactorBy
     */
    public Defactor(Agent agent, SourceCodeQuality codeQuality, int DefactorBy) {
        super(agent);
        this.codeQuality = codeQuality;
        this.DefactorBy = DefactorBy;
    }

    /**
     *
     */
    @Override
    public void action() {
        while (!done()) {

            if (codeQuality.getCodeQuality() == 1) {
                ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
                aclMessage.addReceiver(new AID("System", AID.ISLOCALNAME));
                aclMessage.setContent("die");
                myAgent.send(aclMessage);
                myAgent.doDelete();
            } else {
                if (this.DefactorBy != 0) {
                    codeQuality.decreaseQuality(DefactorBy);
                    System.out.println("Quality decreased by: " + DefactorBy);
                    System.out.println("Code Quality is: " + codeQuality.getCodeQuality());
                } else {
                    codeQuality.decreaseQuality();
                    System.out.println("Code Quality is: " + codeQuality.getCodeQuality());
                }
            }
            done = true;
        }
    }

    @Override
    public boolean done() {
        return done;
    }

}
