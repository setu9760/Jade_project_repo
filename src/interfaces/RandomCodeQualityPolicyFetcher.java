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
package interfaces;

/**
 * This interface needs to implemented by the ReceiveMessage behaviour of
 * SourceCode agent. This implementation gives the programmer freedom to
 * manipulate the policy while coding other features in future implementation.
 * <p>
 * This interface <i>MUST</i> not be implemented by any other agent as the other
 * agents do not make use of it's method in current code.</p>
 *
 *
 * @author Desai
 */
public interface RandomCodeQualityPolicyFetcher {

    /**
     * This method must be implemented in such a way that the resulting change
     * in code quality is decided from this policy.
     *
     * @param changeSize Size of the change applied to the software system.
     * @return the number by which the code quality will be affected.
     */
    public int RandomizeCodeQialityPolicy(int changeSize);

}
