/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.extras;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Desai
 */
public class jadeBootThread extends Thread {

    /**
     */
    private final String jadeBoot_CLASS_NAME = "jade.Boot";
    /**
     */
    private final String MAIN_METHOD_NAME = "main";
    /**
     */
    private final String ACTOR_NAMES_args = "Developer:myjadeinit.actors.Developer;System:myjadeinit.actors.SoftwareSystem;SourceCode:myjadeinit.actors.SourceCode;User:myjadeinit.actors.User;Manager:myjadeinit.actors.ProjectManager";
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

    public jadeBootThread() throws ClassNotFoundException, SecurityException, NoSuchMethodException {
        secondClass = Class.forName(jadeBoot_CLASS_NAME);
        main = secondClass.getMethod(MAIN_METHOD_NAME, String[].class);
        params = new String[]{GUI_args, ACTOR_NAMES_args};
    }

    @Override
    public void run() {
        try {
            main.invoke(null, new Object[]{params});
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(jadeBootThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
