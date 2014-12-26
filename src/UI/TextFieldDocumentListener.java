/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import game.Board;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

/**
 *
 * @author felipe
 */
public class TextFieldDocumentListener implements DocumentListener {
    
    JTextField textField;
    String textFromCell, textName;
    int insertedNumber;
    Document doc;
    Board board;
    
    public TextFieldDocumentListener(Board b, JTextField text) {
        board = b;
        textField = text;
    }
    
    @Override
    public void insertUpdate(DocumentEvent e) {
        doc = e.getDocument();
        try {
            sendUserInput(Integer.valueOf(doc.getText(0, doc.getLength())));            
        } catch (BadLocationException ex) {
            Logger.getLogger(TextFieldDocumentListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        doc = e.getDocument();
        try {
            sendUserInput(Integer.valueOf(doc.getText(0, doc.getLength())));
        } catch (BadLocationException ex) {
            Logger.getLogger(TextFieldDocumentListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sendUserInput(int number) {
        board.getInput(number);
        if(board.validateCell()) {
            textField.setEditable(false);
        }
    }
}
