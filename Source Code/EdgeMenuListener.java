import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

public class EdgeMenuListener implements ActionListener {
 

  
  
   public JMenuItem jmiDTOpenEdge, jmiDTOpenSave, jmiDTSave, jmiDTSaveAs, jmiDTExit, jmiDTOptionsOutputLocation, jmiDTOptionsShowProducts, jmiDTHelpAbout;
   public JMenuItem jmiDROpenEdge, jmiDROpenSave, jmiDRSave, jmiDRSaveAs, jmiDRExit, jmiDROptionsOutputLocation, jmiDROptionsShowProducts, jmiDRHelpAbout;
   
   public EdgeTable[] tables; //master copy of EdgeTable objects
   public EdgeField[] fields; //master copy of EdgeField objects


     
   public boolean dataSaved = true;


   public JFileChooser jfcEdge;
 
   public EdgeConvertFileParser ecfp;

  public ExampleFileFilter effEdge, effSave, effClass;

   public EdgeMenuListener(){
   
   
   
   
   
   
   }//end of EdgeMEnuListener constructor
 
 
 
   public void actionPerformed(ActionEvent ae) {
      int returnVal;
      if ((ae.getSource() == jmiDTOpenEdge) || (ae.getSource() == jmiDROpenEdge)) {
         if (!dataSaved) {
            int answer = JOptionPane.showConfirmDialog(null, "You currently have unsaved data. Continue?",
                                                       "Are you sure?", JOptionPane.YES_NO_OPTION);
            if (answer != JOptionPane.YES_OPTION) {
               return;
            }
         }
         jfcEdge.addChoosableFileFilter(effEdge);
         returnVal = jfcEdge.showOpenDialog(null);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
            parseFile = jfcEdge.getSelectedFile();
            ecfp = new EdgeConvertFileParser(parseFile);
            tables = ecfp.getEdgeTables();
            for (int i = 0; i < tables.length; i++) {
               tables[i].makeArrays();
            }
            fields = ecfp.getEdgeFields();
            ecfp = null;
            populateLists();
            saveFile = null;
            jmiDTSave.setEnabled(false);
            jmiDRSave.setEnabled(false);
            jmiDTSaveAs.setEnabled(true);
            jmiDRSaveAs.setEnabled(true);
            jbDTDefineRelations.setEnabled(true);
         
            jbDTCreateDDL.setEnabled(true);
            jbDRCreateDDL.setEnabled(true);
            
            truncatedFilename = parseFile.getName().substring(parseFile.getName().lastIndexOf(File.separator) + 1);
            jfDT.setTitle(DEFINE_TABLES + " - " + truncatedFilename);
            jfDR.setTitle(DEFINE_RELATIONS + " - " + truncatedFilename);
         } 
         else {
            return;
         }
         dataSaved = true;
      }
      
      if ((ae.getSource() == jmiDTOpenSave) || (ae.getSource() == jmiDROpenSave)) {
         if (!dataSaved) {
            int answer = JOptionPane.showConfirmDialog(null, "You currently have unsaved data. Continue?",
                                                       "Are you sure?", JOptionPane.YES_NO_OPTION);
            if (answer != JOptionPane.YES_OPTION) {
               return;
            }
         }
         jfcEdge.addChoosableFileFilter(effSave);
         returnVal = jfcEdge.showOpenDialog(null);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
            saveFile = jfcEdge.getSelectedFile();
            ecfp = new EdgeConvertFileParser(saveFile);
            tables = ecfp.getEdgeTables();
            fields = ecfp.getEdgeFields();
            ecfp = null;
            populateLists();
            parseFile = null;
            jmiDTSave.setEnabled(true);
            jmiDRSave.setEnabled(true);
            jmiDTSaveAs.setEnabled(true);
            jmiDRSaveAs.setEnabled(true);
            jbDTDefineRelations.setEnabled(true);
         
            jbDTCreateDDL.setEnabled(true);
            jbDRCreateDDL.setEnabled(true);
         
            truncatedFilename = saveFile.getName().substring(saveFile.getName().lastIndexOf(File.separator) + 1);
            jfDT.setTitle(DEFINE_TABLES + " - " + truncatedFilename);
            jfDR.setTitle(DEFINE_RELATIONS + " - " + truncatedFilename);
         } 
         else {
            return;
         }
         dataSaved = true;
      }
      
      if ((ae.getSource() == jmiDTSaveAs) || (ae.getSource() == jmiDRSaveAs) ||
          (ae.getSource() == jmiDTSave) || (ae.getSource() == jmiDRSave)) {
         if ((ae.getSource() == jmiDTSaveAs) || (ae.getSource() == jmiDRSaveAs)) {
            saveAs();
         } 
         else {
            writeSave();
         }
      }
      
      if ((ae.getSource() == jmiDTExit) || (ae.getSource() == jmiDRExit)) {
         if (!dataSaved) {
            int answer = JOptionPane.showOptionDialog(null,
                "You currently have unsaved data. Would you like to save?",
                "Are you sure?",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, null, null);
            if (answer == JOptionPane.YES_OPTION) {
               if (saveFile == null) {
                  saveAs();
               }
            }
            if ((answer == JOptionPane.CANCEL_OPTION) || (answer == JOptionPane.CLOSED_OPTION)) {
               return;
            }
         }
         System.exit(0); //No was selected
      }
      
      if ((ae.getSource() == jmiDTOptionsOutputLocation) || (ae.getSource() == jmiDROptionsOutputLocation)) {
         setOutputDir();
      }
   
      if ((ae.getSource() == jmiDTOptionsShowProducts) || (ae.getSource() == jmiDROptionsShowProducts)) {
         JOptionPane.showMessageDialog(null, "The available products to create DDL statements are:\n" + displayProductNames());
      }
      
      if ((ae.getSource() == jmiDTHelpAbout) || (ae.getSource() == jmiDRHelpAbout)) {
         JOptionPane.showMessageDialog(null, "EdgeConvert ERD To DDL Conversion Tool\n" +
                                             "by Stephen A. Capperell\n" +
                                             "© 2007-2008");
      }
   } // EdgeMenuListener.actionPerformed()
} // EdgeMenuListener
