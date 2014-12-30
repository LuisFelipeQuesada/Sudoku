/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import UI.MainWindow.WinningWindow;
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
    MainWindow main;
    Object[] options = {"Jugar de nuevo", "No"};
    
    public TextFieldKeyListener(Board b, MainWindow win) {
        board = b;
        main = win;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        getUserInput(e);
        //countUserInput(e);
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

            // Basado en el nombre del textfield se obtiene el nombre
            String originName = origin.getName();
            index = originName.indexOf('.');
            row = Integer.valueOf(originName.substring(0, index));
            col = Integer.valueOf(originName.substring(index + 1, originName.length()));

            if(board.validateInput(row, col, numberEntered)) {
                // If you want know if the answer is correct, the change the color by this one: #009966
                origin.setForeground(Color.decode("#000000"));
                if(board.isWon()) {
                    WinningWindow win = main.new WinningWindow();
                }
            }
            else {
                // If the result is not valid, the number is put in red
                //origin.setForeground(Color.decode("#CC0000"));
            }
        } catch(NumberFormatException exception) {}
    }
    
    private boolean isNumber(Object num) {
        return true;
    }
    
    /*private boolean countUserInput(KeyEvent e) {
        boolean res = false;
        String userInput = (((JTextField) e.getComponent()).getText());
        if(userInput.length() > 1) {
            (((JTextField) e.getComponent()).
        }
        return res;
    }*/
}
