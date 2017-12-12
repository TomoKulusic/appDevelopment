import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

public class EdgeWindowListener implements WindowListener {

   public boolean dataSaved = true;
   public JFrame jfDT, jfDR;
   public  File parseFile, saveFile, outputFile, outputDir, outputDirOld;
   EdgeConvertGUI gui;

   public EdgeWindowListener(){
   
      this.jfDT = jfDT;
      this.jfDR = jfDR;
      this.saveFile = saveFile;
   
   
   }



   public void windowActivated(WindowEvent we) {}
   public void windowClosed(WindowEvent we) {}
   public void windowDeactivated(WindowEvent we) {}
   public void windowDeiconified(WindowEvent we) {}
   public void windowIconified(WindowEvent we) {}
   public void windowOpened(WindowEvent we) {}
   
   public void windowClosing(WindowEvent we) {
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
            gui.writeSave();
         }
         if ((answer == JOptionPane.CANCEL_OPTION) || (answer == JOptionPane.CLOSED_OPTION)) {
            if (we.getSource() == jfDT) {
               jfDT.setVisible(true);
            }
            if (we.getSource() == jfDR) {
               jfDR.setVisible(true);
            }
            return;
         }
      }
      System.exit(0); //No was selected
   }
}
