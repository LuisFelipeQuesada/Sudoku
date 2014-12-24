/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Files.ReadXml;
import game.Block;
import game.Board;
/**
 *
 * @author felipe
 */
public class MainWindow {
    
    JFrame window = null;
    GridLayout optionsLayout, boardLayout, blockLayout = null;
    BorderLayout mainLayout = null;
    JPanel windowPanel, panelOptions, boardPanelPrin, boardPanel, boardPanelBack, blockPanel = null;
    JTextField text = null;
    JButton buttonOption, buttonBack = null;
            
    public MainWindow() {
        window = new JFrame("Sudoku");
        window.setSize(500, 530);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        windowPanel = new JPanel(new CardLayout());
        windowPanel.add("panelOptions", initGUI());
        windowPanel.add("boardPanel", createBoardPanel());
        
        mainLayout = new BorderLayout();
        window.setLayout(mainLayout);
        window.add(addMenu(), mainLayout.NORTH);
        window.add(windowPanel, mainLayout.CENTER);
    }
    
    public JMenuBar addMenu() {
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        bar.add(file);
        return bar;
    }
    
    public JPanel initGUI() {
        panelOptions = new JPanel();
        optionsLayout = new GridLayout(3, 1);
        panelOptions.setLayout(optionsLayout);
        for(int i = 0; i < 3; i++) {
            panelOptions.add(createOptions(String.valueOf(i)));
        }
        return panelOptions;
    }
    
    public JButton createOptions(String num) {
        buttonOption = new JButton("Option " + num);
        buttonOption.setName(num);
        buttonOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton src = (JButton)e.getSource();
                if(Integer.valueOf(src.getName()) == 0) {
                    ReadXml xml = new ReadXml();
                    Board board = new Board();
                }
                else if(Integer.valueOf(src.getName()) == 1) {
                    // Cargar sudoku medio
                }
                else {
                    // Cargar sudoku difícil
                }
                CardLayout cardLayout = (CardLayout) windowPanel.getLayout();
                cardLayout.show(windowPanel, "boardPanel");
            }
        });
        return buttonOption;
    }
    
    public JPanel createBoardPanel() {
        boardPanelPrin = new JPanel(new BorderLayout());
        boardPanel = new JPanel();
        boardPanelBack = new JPanel();
        boardLayout = new GridLayout(3, 3);
        boardPanel.setLayout(boardLayout);
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                boardPanel.add(createBlock());
            }
        }
        
        buttonBack = new JButton("Back");
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) windowPanel.getLayout();
                cardLayout.show(windowPanel, "panelOptions");
            }
        });
        
        
        
        boardPanelBack.add(buttonBack);
        
        boardPanelPrin.add(boardPanel, mainLayout.CENTER);
        boardPanelPrin.add(boardPanelBack, mainLayout.SOUTH);
        
        return boardPanelPrin;
    }
    
    public JPanel createBlock() {
        blockPanel = new JPanel();
        blockPanel.setSize(150, 150);
        blockPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                blockPanel.add(boardCells(String.valueOf(i) + "." + String.valueOf(j)));
            }
        }     
        blockLayout = new GridLayout(3, 3);
        blockPanel.setLayout(blockLayout);
        return blockPanel;
    }
    
    public JTextField boardCells(String name) {
        text = new JTextField(1);
        text.setName(name);
        text.setBorder(BorderFactory.createLineBorder(Color.black));
        text.setHorizontalAlignment(JTextField.CENTER);
        Font f = new Font("SansSerif", Font.BOLD, 24);
        text.setFont(f);
        return text;
    }
}
