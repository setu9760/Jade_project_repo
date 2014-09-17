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
package utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The JADE framework requires it's main class to be invoked to start the
 * container window. It also requires that all the agent names and agents paths
 * are passed to is as arguments in order for them to be started in the main
 * container.
 * <p>
 * This class uses java reflection API to find the main class form library and
 * invoke its main method. it also passes the agent names and paths as argument
 * string to it.</p>
 *
 * @author Desai
 */
public class jadeBootThread extends Thread {

    /**
     * The name of the class to be called.
     */
    private final String jadeBoot_CLASS_NAME = "jade.Boot";
    /**
     * The name of the method to be invoked.
     */
    private final String MAIN_METHOD_NAME = "main";
    /**
     * This string is used to call the agents in the simulation model.
     * <i>NOTE: It is very important to define the names and directories
     * properly as it might result in run time error. Especially when changing
     * the directory of agents it is necessary to change this sting accordingly.
     * As these are arguments passed during the runtime it can not be done
     * dynamically.</i>
     */
    private final String ACTOR_NAMES_args = "Developer:actors.Developer;System:actors.SoftwareSystem;SourceCode:actors.SourceCode;User:actors.User;Manager:actors.ProjectManager";
    /**
     */
    private final String GUI_args = "-gui";
    /**
     */
    private final Class<?> secondClass;
    /**
     */
    private final Method main;
    /**
     */
    private final String[] params;

    /**
     * This constructor initialises the variables and if unsuitable variables
     * are passes it throws one of the following error.
     *
     * @throws ClassNotFoundException
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    public jadeBootThread() throws ClassNotFoundException, SecurityException, NoSuchMethodException {
        secondClass = Class.forName(jadeBoot_CLASS_NAME);
        main = secondClass.getMethod(MAIN_METHOD_NAME, String[].class);
        params = new String[]{GUI_args, ACTOR_NAMES_args};
    }

    @Override
    public void run() {
        try {
            main.invoke(null, new Object[]{params});
        } catch (IllegalAccessException |
                IllegalArgumentException |
                InvocationTargetException ex) {
            Logger.getLogger(jadeBootThread.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
}
