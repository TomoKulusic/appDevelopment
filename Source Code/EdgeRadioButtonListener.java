import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

 
 
public class EdgeRadioButtonListener implements ActionListener {


 
   public void actionPerformed(ActionEvent ae) {
      for (int i = 0; i < jrbDataType.length; i++) {
         if (jrbDataType[i].isSelected()) {
            currentDTField.setDataType(i);
            break;
         }
      }
      if (jrbDataType[0].isSelected()) {
         jtfDTVarchar.setText(Integer.toString(EdgeField.VARCHAR_DEFAULT_LENGTH));
         jbDTVarchar.setEnabled(true);
      } 
      else {
         jtfDTVarchar.setText("");
         jbDTVarchar.setEnabled(false);
      }
      jtfDTDefaultValue.setText("");
      currentDTField.setDefaultValue("");
      dataSaved = false;
   }
}