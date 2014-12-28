/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import game.Board;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author felipe
 */
public class TextFieldFocusListener implements FocusListener {
    
    JTextField textField;
    Color color;
    String textFromCell, textName;
    int index, data;
    Board board;
    public static boolean res = false;
    
    public TextFieldFocusListener(Board b) {
        board = b;
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        textField = ((JTextField) e.getSource());
        color = textField.getBackground();
        textField.setBackground(Color.decode("#FFFF99"));
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(textField.getText().equals("")) {
            textField.setBackground(Color.decode("#FFFFFF"));
        }
        else {
            textField.setBackground(Color.decode("#F0F0F0"));
        }
        /*if(textField.getText().equalsIgnoreCase("")) {
            textField.setBackground(Color.decode("#FFFFFF"));
        }
        else {
            if(res) {
                textField.setBackground(Color.decode("#99FFCC")); //00CC66
            }
            else if(!textField.isEditable()) {
                textField.setBackground(color);
            }
            else {
                textField.setBackground(Color.decode("#CC6666")); //CC0000
            }
        }*/
    }
    
    public static boolean getValidation() {
        return res;
    }
}
