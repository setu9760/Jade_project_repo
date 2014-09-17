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

import javax.swing.JOptionPane;

/**
 * This class is developed to show a final dialog box to the user. This class
 * implements runnable so that it could be ran on separate thread.
 * <p>
 * This class contains a showFinalAlert() static method which is called from the
 * run() method of this class. </p>
 *
 * @author S Desai
 */
public class FinalAlert implements Runnable {

    public static JOptionPane finalAlert;
    private static boolean isVisible = false;

    /**
     * This method checks if the windows is already visible. It it is not
     * already visible in displays a alert dialog to inform the user that
     * simulation cycles have been completed.
     */
    public static void showFinalAlert() {
        if (!isVisible) {
            JOptionPane.showConfirmDialog(null, "All Iterations complete", "DONE !!!", JOptionPane.OK_CANCEL_OPTION);
            isVisible = true;
        }
    }

    @Override
    public void run() {
        showFinalAlert();
    }
}
