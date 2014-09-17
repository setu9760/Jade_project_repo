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

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import utils.SourceCodeQuality;

/**
 * THis is a Refactoring behaviour class. This behaviour is applied to the
 * SourceCode agent when the RandomizeCodeQualityFether policy results in
 * increasing the code quality.
 *
 * @author Desai
 */
public class Refactor extends Behaviour {

    /**
     *
     */
    private final SourceCodeQuality codeQuality;
    private boolean done = false;
    private int RefactorBy = 0;

    /**
     * This constructor is used when the code quality is to be increased by two
     * units.
     *
     * @param agent The agent to which this behaviour belongs.
     * @param codeQuality codeQuality Object
     */
    public Refactor(Agent agent, SourceCodeQuality codeQuality) {
        super(agent);
        this.codeQuality = codeQuality;
    }

    /**
     * This constructor is used when the codeQuality is to be increased by a
     * pre-decide number rather than two units.
     *
     * @param agent The agent to which this behaviour belongs
     * @param CodeQuality codeQuality agent
     * @param RefactorBy integer value by which the codeQuality is to be
     * increased.
     */
    public Refactor(Agent agent, SourceCodeQuality CodeQuality, int RefactorBy) {
        super(agent);
        this.codeQuality = CodeQuality;
        this.RefactorBy = RefactorBy;
    }

    @Override
    public void action() {
        while (!done()) {
            if (RefactorBy != 0) {
                codeQuality.increaseQuality(RefactorBy);
                System.out.println("Code Quality increased by: " + RefactorBy);
                System.out.println("Code Quality is: " + codeQuality.getCodeQuality());
            } else {
                codeQuality.increaseQuality();
                System.out.println("Code Quality is: " + codeQuality.getCodeQuality());
            }
            done = true;
        }
    }

    @Override
    public boolean done() {
        return done;
    }
}
