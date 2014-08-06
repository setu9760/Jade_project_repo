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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Desai
 */
public class InitialiseVariable extends javax.swing.JFrame {

    /**
     * This is default software size which is 100.
     */
    public static int SoftSize = 100;

    /**
     * This is default code quality which is 50;
     */
    public static int CodeQuality = 50;

    /**
     * This is default timeInteval between each iteration/cycle of evaluation;
     */
    public static long timeInterval = 5000L;

    /**
     * This is default number of cycles which is 50.
     */
    public static int numOfCycles = 20;

    /**
     * Creates new form NewJFrame
     */
    public InitialiseVariable() {

        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox2 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Select number of cycles in simulation:");

        jLabel3.setText("Select time gap in each simulation:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Default", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100", "120", "140", "160", "180", "200" }));
        jComboBox1.setToolTipText("Please select number of simulations");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Default", "1", "2", "5", "10", "15", "12" }));

        jLabel4.setText("Initial Software size:");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Default", "10", "20", "40", "60", "80", "100" }));

        jLabel5.setText("Initial Code quality:");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Default", "40", "50", "60" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jButton1.setText("Continue");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Second/s");

        jLabel7.setText("Units");

        jLabel8.setText("Units");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(40, 40, 40))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel1)))
                .addGap(36, 36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(33, 33, 33))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (!isEmpty(jComboBox1) && !isEmpty(jComboBox3) && !isEmpty(jComboBox4) && !isEmpty(jComboBox5)) {
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            String numOfCycles = jComboBox1.getSelectedItem().toString();
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            String timeInterval = jComboBox3.getSelectedItem().toString();
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            String SoftSize = jComboBox4.getSelectedItem().toString();
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            String CodeQuality = jComboBox5.getSelectedItem().toString();

            String all = "Number of Cycles: " + numOfCycles;
            all += "\nTime Interval: " + timeInterval;
            all += "\nSoftware Size: " + SoftSize;
            all += "\nCode Quality: " + CodeQuality;

            try {
                InitialiseVariable.SoftSize = Integer.parseInt(SoftSize);
                InitialiseVariable.CodeQuality = Integer.parseInt(CodeQuality);
                InitialiseVariable.timeInterval = Long.parseLong(timeInterval) * 1000; //multipying by 1000 because the user selects seconds and we need millisecods as parameter for thread sleep function.
                InitialiseVariable.numOfCycles = Integer.parseInt(numOfCycles);
            } catch (NumberFormatException ex) {
                System.out.println("Proper software size or code quality not selected so using default size and quality");
                Logger.getLogger(InitialiseVariable.class.getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showConfirmDialog(this, all, "", JOptionPane.YES_NO_CANCEL_OPTION);
            
            
            dispose();
            try {
                final Class clazz = Class.forName("jade.Boot");
                final Method main = clazz.getMethod("main", String[].class);
                final String[] params = {"-gui", "Developer:myjadeinit.actors.Developer;System:myjadeinit.actors.SoftwareSystem;SourceCode:myjadeinit.actors.SourceCode;User:myjadeinit.actors.User;Manager:myjadeinit.actors.ProjectManager"};
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            main.invoke(null, new Object[]{params});
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            Logger.getLogger(InitialiseVariable.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }).start();
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException ex) {
                Logger.getLogger(InitialiseVariable.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "You did not select one of the option properly !!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    public boolean isEmpty(JComboBox jbox) {

        System.out.println("selcted item is: " + jbox.getSelectedItem().toString());

        return jbox.getSelectedItem().toString().equals(" ");
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        jComboBox1.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
