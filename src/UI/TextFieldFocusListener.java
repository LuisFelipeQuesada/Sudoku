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
    
    public TextFieldFocusListener(Board b) {
        board = b;
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        textField = ((JTextField) e.getSource());
        color = textField.getBackground();
        textField.setBackground(Color.decode("#FFFF99"));
        textName = textField.getName();
        index = textName.indexOf('.');
        int row = Integer.valueOf(textName.substring(0, index));
        int col = Integer.valueOf(textName.substring(index + 1, textName.length()));
        sendUserInput(row, col);
    }

    @Override
    public void focusLost(FocusEvent e) {
        textField.setBackground(color);
    }
    
    public void sendUserInput(int row, int col) {
        board.getCell(row, col);
    }
    
}
