/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Color;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

/**
 *
 * @author felipe
 */
public class TextFieldActionListener implements ActionListener {
    
    JTextField textField;
    String textFromCell, textName;
    int row, col, index, data;
    
    public TextFieldActionListener(JTextField text_field) {
        textField = text_field;
    }
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        textFromCell = textField.getText();
        textName = textField.getName();
        data = Integer.valueOf(textFromCell);
        index = textName.indexOf('.');
        row = Integer.valueOf(textFromCell.substring(0, index));
        col = Integer.valueOf(textFromCell.substring(index + 1, textFromCell.length()));
        textField.setBackground(Color.green);        
    }
    
    public int getData() {
        return data;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return col;
    }
}
