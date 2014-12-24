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
import game.Board;
/**
 *
 * @author Luis Felipe Quesada
 */
public class MainWindow {
    
    JFrame window = null;
    GridLayout optionsLayout, boardLayout = null;
    BorderLayout mainLayout = null;
    JPanel windowPanel, panelOptions, boardPanelPrin, boardPanel, boardPanelBack = null;
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
                    ReadXml xml = new ReadXml(0);
                    Board board = new Board(xml.createMatrix());
                    JTextField cells[][] = createBoardCells();
                    fillBoardCells(cells, board.getBoard());
                    addboardCells(boardPanel, cells);
                }
                else if(Integer.valueOf(src.getName()) == 1) {
                    ReadXml xml = new ReadXml(1);
                    Board board = new Board(xml.createMatrix());
                    JTextField cells[][] = createBoardCells();
                    fillBoardCells(cells, board.getBoard());
                    addboardCells(boardPanel, cells);
                }
                else {
                    ReadXml xml = new ReadXml(2);
                    Board board = new Board(xml.createMatrix());
                    JTextField cells[][] = createBoardCells();
                    fillBoardCells(cells, board.getBoard());
                    addboardCells(boardPanel, cells);
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
        boardLayout = new GridLayout(9, 9);
        boardPanel.setLayout(boardLayout);
        
        buttonBack = new JButton("Back");
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) windowPanel.getLayout();
                cardLayout.show(windowPanel, "panelOptions");
                boardPanel.removeAll();
            }
        });
        boardPanelBack.add(buttonBack);
        
        boardPanelPrin.add(boardPanel, mainLayout.CENTER);
        boardPanelPrin.add(boardPanelBack, mainLayout.SOUTH);
        
        return boardPanelPrin;
    }
    
    // Sett the block in the parent: boardPanel
    public void setBlocks() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                boardPanel.add(createBlock());
            }
        }
    }
    
    public JPanel createBlock() {
        JPanel blockPanel = new JPanel();
        blockPanel.setSize(150, 150);
        blockPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        GridLayout blockLayout = new GridLayout(3, 3);
        blockPanel.setLayout(blockLayout);
        
        return blockPanel;
    }
    
    // Creates the cells and adds them to the parent
    public void addboardCells(JPanel parent, JTextField[][] cellsMatrix) {
        JTextField text = null;
        for(int row = 0; row < cellsMatrix.length; row++) {
            for(int col = 0; col < cellsMatrix[row].length; col++) {
                parent.add(cellsMatrix[row][col]);
            }
        }
    }
    
    private JTextField[][] createBoardCells() {
        JTextField[][] mat = new JTextField[9][9];
        JTextField text = null;
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                text = new JTextField(1);
                text.setBorder(BorderFactory.createLineBorder(Color.black));
                text.setHorizontalAlignment(JTextField.CENTER);
                Font f = new Font("SansSerif", Font.BOLD, 24);
                text.setFont(f);
                mat[row][col] = text;
            }
        }
        return mat;
    }
    
    private void fillBoardCells(JTextField[][] cellsMatrix, int[][] data) {
        JTextField matrix[][] = cellsMatrix;
        for(int row = 0; row < data.length; row++) {
            for(int col = 0; col < data[row].length; col++) {
                matrix[row][col].setText(String.valueOf(data[row][col]));
            }
        }
    }    
}
