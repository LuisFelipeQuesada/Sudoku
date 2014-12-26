/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import Files.ReadXml;
import game.Board;
import java.util.ArrayList;
/**
 *
 * @author Luis Felipe Quesada
 */
public class MainWindow {
    
    JFrame window = null;
    GridLayout optionsLayout, boardLayout = null;
    BorderLayout mainLayout = null;
    JPanel windowPanel, panelOptions, panelOptionsPrin, boardPanelPrin, boardPanel, boardPanelFooter = null;
    JButton buttonOption, buttonBack, buttonClose = null;
    ArrayList<JPanel> blocksList = null;
    JTextField text, textField = null;
    String textFromCell, textName;
    int row, col, index, data;
    Board board = null;
            
    public MainWindow() {
        blocksList = new ArrayList();
        window = new JFrame("Sudoku");
        window.setSize(530, 530);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        boardPanelFooter = new JPanel();
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
        panelOptionsPrin = new JPanel(new BorderLayout());
        panelOptions = new JPanel();
        optionsLayout = new GridLayout(3, 1);
        panelOptions.setLayout(optionsLayout);
        for(int i = 0; i < 3; i++) {
            panelOptions.add(createOptions(String.valueOf(i)), BorderLayout.NORTH);
        }
        
        panelOptionsPrin.add(panelOptions, BorderLayout.CENTER);
        return panelOptionsPrin;
    }
    
    public JButton createOptions(String num) {
        buttonOption = new JButton("Option " + num);
        buttonOption.setName(num);
        buttonOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton src = (JButton)e.getSource();
                if(Integer.valueOf(src.getName()) == 0) {
                    blocksList.clear();
                    ReadXml xml = new ReadXml(0);
                    board = new Board(xml.createMatrix(), 0);
                    JTextField cells[][] = createBoardCells();
                    fillBoardCells(cells, board.getUserBoard());
                    setBlocks(boardPanel);
                    addBoardCells(blocksList, cells);
                }
                else if(Integer.valueOf(src.getName()) == 1) {
                    blocksList.clear();
                    ReadXml xml = new ReadXml(1);
                    board = new Board(xml.createMatrix(), 1);
                    JTextField cells[][] = createBoardCells();
                    fillBoardCells(cells, board.getUserBoard());
                    setBlocks(boardPanel);
                    addBoardCells(blocksList, cells);
                }
                else {
                    blocksList.clear();
                    ReadXml xml = new ReadXml(2);
                    board = new Board(xml.createMatrix(), 2);
                    JTextField cells[][] = createBoardCells();
                    fillBoardCells(cells, board.getUserBoard());
                    setBlocks(boardPanel);
                    addBoardCells(blocksList, cells);
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
        boardLayout = new GridLayout(3, 3);
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
        
        boardPanelFooter = new JPanel();
        boardPanelFooter.add(buttonBack);
        
        boardPanelPrin.add(boardPanel, mainLayout.CENTER);
        boardPanelPrin.add(boardPanelFooter, mainLayout.SOUTH);
        
        return boardPanelPrin;
    }
    
    // Sett the block in the parent: boardPanel
    public void setBlocks(JPanel parent) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                parent.add(createBlock());
            }
        }
    }
    
    public JPanel createBlock() {
        JPanel blockPanel = new JPanel();
        GridLayout blockLayout = new GridLayout(3, 3);
        blockPanel.setLayout(blockLayout);
        blockPanel.setSize(150, 150);
        blockPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#CC0000")));
        blocksList.add(blockPanel);
        return blockPanel;
    }
    
    // Creates the cells and adds them to the parent
    public void addBoardCells(ArrayList<JPanel> parentsList, JTextField[][] cellsMatrix) {
        int parentIndex = 0;
        int counter = 0;
        for(int row = 0; parentIndex < 9; row++) {
            // Se envia la siguiente fila de la matrix
            addRow(parentsList, cellsMatrix[row], parentIndex);
            counter += 1;
            if(counter >= 3) {
                parentIndex += 3;
                counter = 0;
            }
        }
    }
    
    public void addRow(ArrayList<JPanel> parentsList, JTextField[] row, int index) {
        int parentIndex = index;
        for(int col = 0; col < row.length; col++) {
            parentsList.get(parentIndex).add(row[col]);
            if((col == 2) || (col == 5)) {
                parentIndex += 1;
            }
        }
    }
    
    private JTextField[][] createBoardCells() {
        JTextField[][] mat = new JTextField[9][9];
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                text = new JTextField(1);
                text.setName(String.valueOf(row) + "." + String.valueOf(col));
                text.setBorder(BorderFactory.createLineBorder(Color.decode("#CC0000")));
                text.setHorizontalAlignment(JTextField.CENTER);
                
                // Agregar tipo de Font al TextField
                Font f = new Font("SansSerif", Font.ROMAN_BASELINE, 24);
                text.setFont(f);
                
                // Agregar Listeners al TextField
                text.addFocusListener(new TextFieldFocusListener(board));
                text.getDocument().addDocumentListener(new TextFieldDocumentListener(board, text));
                
                // Agregar textfield a la matriz
                mat[row][col] = text;
            }
            text = new JTextField(1);
        }
        return mat;
    }
    
    private void fillBoardCells(JTextField[][] cellsMatrix, int[][] data) {
        JTextField matrix[][] = cellsMatrix;
        for(int row = 0; row < data.length; row++) {
            for(int col = 0; col < data[row].length; col++) {
                if(data[row][col] == 0) {
                    matrix[row][col].setText("");
                }
                else {
                    matrix[row][col].setText(String.valueOf(data[row][col]));
                    matrix[row][col].setEditable(false);
                }
            }
        }
    }
}
