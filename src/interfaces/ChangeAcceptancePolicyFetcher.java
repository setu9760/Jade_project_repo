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
 * This interface is implemented with intension to give the developer freedom to
 * implement the policy as per the requirement. Currently in this model this
 * interface has implementation in Developer agent.
 *
 * @author Desai
 */
public interface ChangeAcceptancePolicyFetcher {

    /**
     * @param changeRequestSize The size of change request.
     * @return This value depends upon the developer's policy
     * implementation.
     */
    public int ChangeAcceptancePolicy(int changeRequestSize);
}
