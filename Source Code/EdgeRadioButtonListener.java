import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

 
 
public class EdgeRadioButtonListener implements ActionListener {

   public JRadioButton[] jrbDataType;
   public EdgeField currentDTField;
   public JTextField jtfDTVarchar, jtfDTDefaultValue;

   public JButton jbDTCreateDDL, jbDTDefineRelations, jbDTVarchar;
   boolean dataSaved;
    
   public EdgeRadioButtonListener(JRadioButton[] _jrbDataType, EdgeField _currentDTField, JButton _jbDTVarchar,JTextField _jtfDTDefaultValue ){
      
      this.jrbDataType = _jrbDataType;
      this.currentDTField = _currentDTField;
      this.jbDTVarchar = _jbDTVarchar;
      this.jtfDTDefaultValue =  _jtfDTDefaultValue;
      
   }
 
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