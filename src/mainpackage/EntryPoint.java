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
package mainpackage;

import utils.InitialiseVariable;

/**
 * As the name suggests this class is the entry point to the program. The main
 * method of this class is called to start the program.
 *
 * @author Desai
 */
public class EntryPoint {

    public static void main(String[] args) {
        InitialiseVariable frame = new InitialiseVariable();
        frame.setVisible(true);
    }
}
