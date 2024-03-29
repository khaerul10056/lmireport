/*
 * Copyright (C) 2005 - 2008 JasperSoft Corporation.  All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased a commercial license agreement from JasperSoft,
 * the following license terms apply:
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; and without the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see http://www.gnu.org/licenses/gpl.txt
 * or write to:
 *
 * Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330,
 * Boston, MA  USA  02111-1307
 *
 *
 *
 *
 * CompatibilityDialog.java
 *
 * Created on 2 giugno 2003, 22.30
 *
 */

package it.businesslogic.ireport.gui;

import it.businesslogic.ireport.util.Misc;
import it.businesslogic.ireport.*;
import it.businesslogic.ireport.util.I18n;
/**
 *
 * @author  Administrator
 */
public class CompatibilityDialog extends javax.swing.JDialog {

    MainFrame mf = null;


    public CompatibilityDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.mf = (MainFrame)parent;
        initComponents();
        applyI18n();
        
        this.setSize(330, 150);

        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR060, "JasperReports 0.6.0"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR061, "JasperReports 0.6.1"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR062, "JasperReports 0.6.2"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR063, "JasperReports 0.6.3"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR064, "JasperReports 0.6.4 - 0.6.5"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR066, "JasperReports 0.6.6"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR067, "JasperReports 0.6.7 - 0.6.8"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR069, "JasperReports 0.6.9"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR100, "JasperReports 1.0.0"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR103, "JasperReports 1.0.3"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR110, "JasperReports 1.1.0"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR111, "JasperReports 1.1.1"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR120, "JasperReports 1.2.0"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR125, "JasperReports 1.2.5"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR126, "JasperReports 1.2.6"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR127, "JasperReports 1.2.7"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR128, "JasperReports 1.2.8"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR129, "JasperReports 1.2.9"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR130, "JasperReports 1.3.0"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR131, "JasperReports 1.3.1"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR132, "JasperReports 1.3.2"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR134, "JasperReports 1.3.4"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR200, "JasperReports 2.0.0 - 2.0.1"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR204, "JasperReports 2.0.2 - 2.0.4"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.JR205, "JasperReports 2.0.5"));
        jComboBox1.addItem(new JRVersionItem( CompatibilitySupport.LAST_AVAILABLE_VERSION, it.businesslogic.ireport.util.I18n.getString("lastAvailableVersion")));

        load();
        Misc.centerFrame(this);



        javax.swing.KeyStroke escape =  javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0, false);
        javax.swing.Action escapeAction = new javax.swing.AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                jButtonCancelActionPerformed(e);
            }
        };

        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);


        //to make the default button ...
        this.getRootPane().setDefaultButton(this.jButtonOk);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButtonOk = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setTitle(it.businesslogic.ireport.util.I18n.getString("compatibilityOptions", "Compatibility options"));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), it.businesslogic.ireport.util.I18n.getString("jasperRepVerComp", "JasperReports Version Compatibility"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11)));
        jPanel9.setMinimumSize(new java.awt.Dimension(250, 150));
        jPanel9.setPreferredSize(new java.awt.Dimension(250, 150));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel9.add(jComboBox1, gridBagConstraints);

        jPanel1.add(jPanel9, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonOk.setMnemonic('o');
        jButtonOk.setText("Ok");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jPanel2.add(jButtonOk);

        jButtonCancel.setText("Cancel");
        jButtonCancel.setMnemonic('c');
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jPanel2.add(jButtonCancel);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        close();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        save();
        close();
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        // Add your handling code here:
    }//GEN-LAST:event_closeDialog

    private void load(){
        if (mf == null || mf.getProperties() == null) {
            return;
        }
        java.util.Properties prop = mf.getProperties();

        try {

            for (int i=0; i<jComboBox1.getItemCount(); ++i)
            {

                JRVersionItem jvi = (JRVersionItem)jComboBox1.getItemAt(i);
                if (jvi.getVersion() == CompatibilitySupport.version)
                {
                    jComboBox1.setSelectedIndex(i);
                    break;
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void save(){
        if (mf == null || mf.getProperties() == null) {
            return;
        }
        java.util.Properties prop = mf.getProperties();

        try {
            int compatibility = CompatibilitySupport.LAST_AVAILABLE_VERSION;

            if (jComboBox1.getSelectedItem() != null)
            {
                compatibility = ((JRVersionItem)jComboBox1.getSelectedItem()).getVersion();
            }

           CompatibilitySupport.version = compatibility;
            prop.put("Compatibility", ""+compatibility);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void close(){
        setVisible(false);
        dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new CompatibilityDialog(new javax.swing.JFrame(), true).setVisible(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables

    public void applyI18n(){
                // Start autogenerated code ----------------------
                jButtonCancel.setText(I18n.getString("compatibilityDialog.buttonCancel","Cancel"));
                jButtonOk.setText(I18n.getString("compatibilityDialog.buttonOk","Ok"));
                // End autogenerated code ----------------------
                ((javax.swing.border.TitledBorder)jPanel9.getBorder()).setTitle( it.businesslogic.ireport.util.I18n.getString("jasperRepVerComp", "JasperReports Version Compatibility") );
                jButtonCancel.setMnemonic(I18n.getString("compatibilityDialog.buttonCancelMnemonic","c").charAt(0));
                jButtonOk.setMnemonic(I18n.getString("compatibilityDialog.buttonOkMnemonic","o").charAt(0));
    }
}


class JRVersionItem
{
    private int version = 0;
    private String desc = "";

    public JRVersionItem(int version, String desc)
    {
        this.version = version;
        this.desc = desc;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String toString()
    {
        return desc;
    }
}
