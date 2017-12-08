import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;


public class CreateDDLButtonListener implements ActionListener {
   private File parseFile, saveFile, outputFile, outputDir, outputDirOld;
   EdgeConvertGUI gui;
   String sqlString;
   public CreateDDLButtonListener(File _outputDir){
   
      this.outputDir = outputDir;
   
   }
   
   
   public void actionPerformed(ActionEvent ae) {
      while (outputDir == null) {
         JOptionPane.showMessageDialog(null, "You have not selected a path that contains valid output definition files yet.\nPlease select a path now.");
         //gui.setOutputDir(outputDir);
      }
      gui.getOutputClasses(); //in case outputDir was set before a file was loaded and EdgeTable/EdgeField objects created
      sqlString = gui.getSQLStatements();
      if (sqlString.equals(EdgeConvertGUI.CANCELLED)) {
         return;
      }
      gui.writeSQL(sqlString);
   }
}