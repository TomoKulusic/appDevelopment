import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

public class EdgeMenuListener implements ActionListener {
   public static final int HORIZ_SIZE = 635;
   public static final int VERT_SIZE = 400;
   public static final int HORIZ_LOC = 100;
   public static final int VERT_LOC = 100;
   public static final String DEFINE_TABLES = "Define Tables";
   public static final String DEFINE_RELATIONS = "Define Relations";
   public static final String CANCELLED = "CANCELLED";
   private static JFileChooser jfcEdge, jfcGetClass, jfcOutputDir;
   private static ExampleFileFilter effEdge, effSave, effClass;
   private File parseFile, saveFile, outputFile, outputDir, outputDirOld;
   private String truncatedFilename;
   private String sqlString;
   private String databaseName;

   private EdgeConvertFileParser ecfp;
   private EdgeConvertCreateDDL eccd;
   private static PrintWriter pw;
   private EdgeTable[] tables; //master copy of EdgeTable objects
   private EdgeField[] fields; //master copy of EdgeField objects
   private EdgeTable currentDTTable, currentDRTable1, currentDRTable2; //pointers to currently selected table(s) on Define Tables (DT) and Define Relations (DR) screens
   private EdgeField currentDTField, currentDRField1, currentDRField2; //pointers to currently selected field(s) on Define Tables (DT) and Define Relations (DR) screens
   private static boolean readSuccess = true; //this tells GUI whether to populate JList components or not
   private boolean dataSaved = true;
   private ArrayList alSubclasses, alProductNames;
   private String[] productNames;
   private Object[] objSubclasses;

   //Define Tables screen objects
   static JFrame jfDT;
   static JPanel jpDTBottom, jpDTCenter, jpDTCenter1, jpDTCenter2, jpDTCenterRight, jpDTCenterRight1, jpDTCenterRight2, jpDTMove;
   static JButton jbDTCreateDDL, jbDTDefineRelations, jbDTVarchar, jbDTDefaultValue, jbDTMoveUp, jbDTMoveDown;
   static ButtonGroup bgDTDataType;
   static JRadioButton[] jrbDataType;
   static String[] strDataType;
   static JCheckBox jcheckDTDisallowNull, jcheckDTPrimaryKey;
   static JTextField jtfDTVarchar, jtfDTDefaultValue;
   static JLabel jlabDTTables, jlabDTFields;
   static JScrollPane jspDTTablesAll, jspDTFieldsTablesAll;
   static JList jlDTTablesAll, jlDTFieldsTablesAll;
   static DefaultListModel dlmDTTablesAll, dlmDTFieldsTablesAll;
   static JMenuBar jmbDTMenuBar;
   static JMenu jmDTFile, jmDTOptions, jmDTHelp;
   static JMenuItem jmiDTOpenEdge, jmiDTOpenSave, jmiDTSave, jmiDTSaveAs, jmiDTExit, jmiDTOptionsOutputLocation, jmiDTOptionsShowProducts, jmiDTHelpAbout;
   
   //Define Relations screen objects
   static JFrame jfDR;
   static JPanel jpDRBottom, jpDRCenter, jpDRCenter1, jpDRCenter2, jpDRCenter3, jpDRCenter4;
   static JButton jbDRCreateDDL, jbDRDefineTables, jbDRBindRelation;
   static JList jlDRTablesRelations, jlDRTablesRelatedTo, jlDRFieldsTablesRelations, jlDRFieldsTablesRelatedTo;
   static DefaultListModel dlmDRTablesRelations, dlmDRTablesRelatedTo, dlmDRFieldsTablesRelations, dlmDRFieldsTablesRelatedTo;
   static JLabel jlabDRTablesRelations, jlabDRTablesRelatedTo, jlabDRFieldsTablesRelations, jlabDRFieldsTablesRelatedTo;
   static JScrollPane jspDRTablesRelations, jspDRTablesRelatedTo, jspDRFieldsTablesRelations, jspDRFieldsTablesRelatedTo;
   static JMenuBar jmbDRMenuBar;
   static JMenu jmDRFile, jmDROptions, jmDRHelp;
   static JMenuItem jmiDROpenEdge, jmiDROpenSave, jmiDRSave, jmiDRSaveAs, jmiDRExit, jmiDROptionsOutputLocation, jmiDROptionsShowProducts, jmiDRHelpAbout;

   EdgeConvertGUI gui;
  
     

     
    
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
            gui.populateLists();
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
            gui.populateLists();
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
            gui.saveAs();
         } 
         else {
            gui.writeSave();
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
                  gui.saveAs();
               }
            }
            if ((answer == JOptionPane.CANCEL_OPTION) || (answer == JOptionPane.CLOSED_OPTION)) {
               return;
            }
         }
         System.exit(0); //No was selected
      }
      
      if ((ae.getSource() == jmiDTOptionsOutputLocation) || (ae.getSource() == jmiDROptionsOutputLocation)) {
         gui.setOutputDir();
      }
   
      if ((ae.getSource() == jmiDTOptionsShowProducts) || (ae.getSource() == jmiDROptionsShowProducts)) {
         JOptionPane.showMessageDialog(null, "The available products to create DDL statements are:\n" + gui.displayProductNames());
      }
      
      if ((ae.getSource() == jmiDTHelpAbout) || (ae.getSource() == jmiDRHelpAbout)) {
         JOptionPane.showMessageDialog(null, "EdgeConvert ERD To DDL Conversion Tool\n" +
                                             "by Stephen A. Capperell\n" +
                                             "2007-2008");
      }
   } // EdgeMenuListener.actionPerformed() 
} // EdgeMenuListener
