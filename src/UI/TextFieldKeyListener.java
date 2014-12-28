/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import game.Board;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Luis Felipe Quesada
 */
public class TextFieldKeyListener implements KeyListener {
    
    Board board;
    int row, col, index, numberEntered;
    JTextField origin;
    
    public TextFieldKeyListener(Board b) {
        board = b;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        getUserInput(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    private void getUserInput(KeyEvent e) {
        try {
            numberEntered = Integer.valueOf(String.valueOf(e.getKeyChar()));
            origin = (JTextField) e.getComponent();

            // Based on the name of the origin the row and colum can be obtained
            String originName = origin.getName();
            index = originName.indexOf('.');
            row = Integer.valueOf(originName.substring(0, index));
            col = Integer.valueOf(originName.substring(index + 1, originName.length()));

            if(board.validateInput(row, col, numberEntered)) {
                origin.setForeground(Color.decode("#009966"));  // Puts color in green
            }
            else {
                origin.setForeground(Color.decode("#CC0000"));
                if(board.getIsWon()) {
                    JOptionPane.showInternalMessageDialog((Component) MainWindow.window, "Felicidades, has ganado", "Felicidades!!!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch(NumberFormatException exception) {}
    }
    
    private boolean isNumber(Object num) {
        return true;
    }
}
