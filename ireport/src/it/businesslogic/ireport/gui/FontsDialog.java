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
 * FontsDialog.java
 * 
 * Created on 7 maggio 2003, 23.43
 *
 */

package it.businesslogic.ireport.gui;

import javax.swing.table.*;
import javax.swing.*;
import javax.swing.event.*;
import it.businesslogic.ireport.*;

import java.util.*;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import it.businesslogic.ireport.util.I18n;

/**
 *
 * @author  Administrator
 */
public class FontsDialog extends javax.swing.JDialog
{
   
   /** Creates new form ValuesDialog */
   public FontsDialog(java.awt.Frame parent, boolean modal)
   {
      super(parent, modal);
      initComponents();
      applyI18n();
      this.setSize(490, 250);
      
      DefaultListSelectionModel dlsm =  (DefaultListSelectionModel)this.jTableParameters.getSelectionModel();
      dlsm.addListSelectionListener(new javax.swing.event.ListSelectionListener()
      {
         public void valueChanged(ListSelectionEvent e)
         {
            jTableParametersListSelectionValueChanged(e);
         }
      });
      
      
        javax.swing.KeyStroke escape =  javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0, false);
        javax.swing.Action escapeAction = new javax.swing.AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setVisible(false);
            }
        };
       
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);

      
      // Open in center...
      it.businesslogic.ireport.util.Misc.centerFrame(this);
   }
   
   
   public void jTableParametersListSelectionValueChanged(javax.swing.event.ListSelectionEvent e)
   {
      if (this.jTableParameters.getSelectedRowCount() > 0)
      {
         this.jButtonModifyParameter.setEnabled(true);
         this.jButtonDeleteParameter.setEnabled(true);
      }
      else
      {
         this.jButtonModifyParameter.setEnabled(false);
         this.jButtonDeleteParameter.setEnabled(false);
      }
   }
   
   
   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelParameters = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableParameters = new javax.swing.JTable();
        jPanelButtons = new javax.swing.JPanel();
        jButtonNewParameter = new javax.swing.JButton();
        jButtonModifyParameter = new javax.swing.JButton();
        jButtonDeleteParameter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Report fonts");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanelParameters.setLayout(new java.awt.BorderLayout(2, 2));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTableParameters.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Report font name", "Font", "Default"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableParameters);

        jPanelParameters.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanelButtons.setLayout(new java.awt.GridBagLayout());

        jPanelButtons.setPreferredSize(new java.awt.Dimension(100, 10));
        jPanelButtons.setMinimumSize(new java.awt.Dimension(100, 10));
        jButtonNewParameter.setText("New");
        jButtonNewParameter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewParameterActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 5, 3);
        jPanelButtons.add(jButtonNewParameter, gridBagConstraints);

        jButtonModifyParameter.setText("Modify");
        jButtonModifyParameter.setEnabled(false);
        jButtonModifyParameter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifyParameterActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 5, 3);
        jPanelButtons.add(jButtonModifyParameter, gridBagConstraints);

        jButtonDeleteParameter.setText("Delete");
        jButtonDeleteParameter.setEnabled(false);
        jButtonDeleteParameter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteParameterActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 5, 3);
        jPanelButtons.add(jButtonDeleteParameter, gridBagConstraints);

        jPanelParameters.add(jPanelButtons, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanelParameters, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButtonDeleteParameterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteParameterActionPerformed
       
       jTableParameters.getSelectedRows();
       
       while (this.jTableParameters.getSelectedRow() >= 0)
       {
          int row = this.jTableParameters.getSelectedRow();
          IReportFont font = (IReportFont)jTableParameters.getValueAt(row, 0);
          // Adjust all elements that refer to this font...
          Enumeration e = this.getJReportFrame().getReport().getElements().elements();
          while (e.hasMoreElements())
          {
             ReportElement re = (ReportElement)e.nextElement();
             if (re instanceof TextReportElement &&
             ((TextReportElement)re).getReportFont().equals(font.getReportFont()) )
                ((TextReportElement)re).setReportFont("");
          }
          this.getJReportFrame().getReport().getFonts().remove( font );
          this.jTableParameters.removeRowSelectionInterval(row, row);
       }
       updateReportFonts();
       ((MainFrame)this.getParent()).getElementPropertiesDialog().updateReportFonts();
       ((MainFrame)this.getParent()).getElementPropertiesDialog().updateSelection();
    }//GEN-LAST:event_jButtonDeleteParameterActionPerformed
    
    private void jButtonModifyParameterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifyParameterActionPerformed
       
       JRFontDialog fd = new JRFontDialog(null, true);
       fd.updateFonts( this.getJReportFrame().getMainFrame().getTtfFonts());
       DefaultTableModel dtm = (DefaultTableModel)jTableParameters.getModel();
       IReportFont font = (IReportFont)dtm.getValueAt(jTableParameters.getSelectedRow(), 0);
       
       fd.setIReportFont(font );
       fd.setVisible(true);
       
       if (fd.getDialogResult() == JOptionPane.OK_OPTION)
       {
          IReportFont new_font = fd.getIReportFont();
          // If change the report font name, we must change all references...
          String oldName = font.getReportFont();
          font.setReportFont( new_font.getReportFont() );
          if ( !it.businesslogic.ireport.util.Misc.nvl(oldName,"").equals( it.businesslogic.ireport.util.Misc.nvl(new_font.getReportFont(),"") ) && new_font.getReportFont() !=  null )
          {
             // This modification can occur only in textElements....
             Enumeration e = this.getJReportFrame().getReport().getElements().elements();
             while (e.hasMoreElements())
             {
                ReportElement re = (ReportElement)e.nextElement();
                if (re instanceof TextReportElement &&
                ((TextReportElement)re).getReportFont().equals(oldName))
                   ((TextReportElement)re).setReportFont( new_font.getReportFont() );
             }
          }
          font.setFontName( new_font.getFontName());
          font.setFontSize( new_font.getFontSize());
          font.setBold( new_font.isBold());
          font.setItalic(new_font.isItalic());
          font.setPDFFontName( new_font.getPDFFontName());
          font.setPdfEmbedded( new_font.isPdfEmbedded());
          font.setPdfEncoding( new_font.getPdfEncoding() );
          font.setStrikeTrought( new_font.isStrikeTrought() );
          font.setUnderline( new_font.isUnderline() );
          font.setDefaultFont( new_font.isDefaultFont() );
          //font.setTTFFont( new_font.getTTFFont() );
          
          // Update the table row....
          
          jTableParameters.setValueAt( font, jTableParameters.getSelectedRow(), 0);
          jTableParameters.setValueAt( font.getDescription(), jTableParameters.getSelectedRow(), 1);
          jTableParameters.setValueAt( font.isDefaultFont()+"", jTableParameters.getSelectedRow(), 2);
          
          jTableParameters.updateUI();
          ((MainFrame)this.getParent()).getElementPropertiesDialog().updateReportFonts();
          ((MainFrame)this.getParent()).getElementPropertiesDialog().updateSelection();
          this.getJReportFrame().setIsDocDirty(true);
          this.getJReportFrame().repaint();
       }
    }//GEN-LAST:event_jButtonModifyParameterActionPerformed
    
    private void jButtonNewParameterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewParameterActionPerformed
       JRFontDialog fd = new JRFontDialog(null, true);
       fd.updateFonts( this.getJReportFrame().getMainFrame().getTtfFonts());
       fd.setVisible(true);
       
       if (fd.getDialogResult() == JOptionPane.OK_OPTION)
       {
          IReportFont font = fd.getIReportFont();
          DefaultTableModel dtm = (DefaultTableModel)jTableParameters.getModel();
	  revertDefaultFontProperties();
          dtm.addRow( new Object[]
          {font, font.getDescription(),font.isDefaultFont()+"" });
          //
          this.getJReportFrame().getReport().getFonts().addElement(font);
          /**
           * update text elements and properties frame....
           */
          
          this.getJReportFrame().getMainFrame().getElementPropertiesDialog().updateReportFonts();
       }
    }//GEN-LAST:event_jButtonNewParameterActionPerformed
    
    /** Set defaultFont Property to false for all defined fonts in Report */
    private void revertDefaultFontProperties(){
       DefaultTableModel dtm = (DefaultTableModel)jTableParameters.getModel();
       for(int i=0;i<dtm.getRowCount();i++){
          IReportFont font = (IReportFont)dtm.getValueAt(i, 0);
	  font.setDefaultFont(false);
          jTableParameters.setValueAt( font.isDefaultFont()+"", i, 2);
       }
       jTableParameters.updateUI();
       ((MainFrame)this.getParent()).getElementPropertiesDialog().updateReportFonts();
       ((MainFrame)this.getParent()).getElementPropertiesDialog().updateSelection();
    }

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
       setVisible(false);
    }//GEN-LAST:event_closeDialog
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
       new ValuesDialog(new javax.swing.JFrame(), true).setVisible(true);
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDeleteParameter;
    private javax.swing.JButton jButtonModifyParameter;
    private javax.swing.JButton jButtonNewParameter;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JPanel jPanelParameters;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableParameters;
    // End of variables declaration//GEN-END:variables
    
    private JReportFrame jReportFrame;
    
    /** Getter for property jReportFrame.
     * @return Value of property jReportFrame.
     *
     */
    public it.businesslogic.ireport.gui.JReportFrame getJReportFrame()
    {
       return jReportFrame;
    }
    
    /** Setter for property jReportFrame.
     * @param jReportFrame New value of property jReportFrame.
     *
     */
    public void setJReportFrame(it.businesslogic.ireport.gui.JReportFrame jReportFrame)
    {
       this.jReportFrame = jReportFrame;
       
       
       
       if (jReportFrame == null)
       {
          setVisible(false);
          return;
       }
              this.setTitle(jReportFrame.getReport().getName()+" "+I18n.getString("fontsDialog.fonts"," fonts..."));
       if (isVisible())
       {
          updateReportFonts();
       }
    }
    
    public void updateReportFonts()
    {
       DefaultTableModel dtm = (DefaultTableModel)jTableParameters.getModel();
       dtm.setRowCount(0);
       
       if (jReportFrame != null)
       {
          Enumeration e =jReportFrame.getReport().getFonts().elements();
          while (e.hasMoreElements())
          {
             IReportFont font = (IReportFont)e.nextElement();
             dtm.addRow( new Object[]
             {font, font.getDescription(), font.isDefaultFont()+"" });
          }
       }
    }
    
    public void setVisible(boolean visible)
    {
       if (visible == isVisible()) return;
       super.setVisible(visible);
       if (visible == true)
       {
          this.setJReportFrame(jReportFrame);
       }
    }
    public void applyI18n(){
        // Start autogenerated code ----------------------
        jButtonDeleteParameter.setText(I18n.getString("fontsDialog.buttonDeleteParameter","Delete"));
        jButtonModifyParameter.setText(I18n.getString("fontsDialog.buttonModifyParameter","Modify"));
        jButtonNewParameter.setText(I18n.getString("fontsDialog.buttonNewParameter","New"));
        // End autogenerated code ----------------------

        jTableParameters.getColumnModel().getColumn(0).setHeaderValue( I18n.getString("fontsDialog.tablecolumn.reportFontName","Report font name") );
        jTableParameters.getColumnModel().getColumn(1).setHeaderValue( I18n.getString("fontsDialog.tablecolumn.font","Font") );
        jTableParameters.getColumnModel().getColumn(2).setHeaderValue( I18n.getString("fontsDialog.tablecolumn.default","Default") );
        this.setTitle(I18n.getString("fontsDialog.title","Report fonts"));
    }
}
