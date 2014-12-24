/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author LuisFelipe
 */
public class Block {
    
    private int[][] matrix;
    private int row, col;
    
    public Block(int[][] matrix) {
        this.matrix = matrix;
        row = 0;
        col = 0;
    }
    
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
    
    public int[][] getMatrix() {
        return matrix;
    }
    
    public void setRow(int row) {
        this.row = row;
    }
    
    public int getRow() {
        return row;
    }
    
    public void setCol(int col) {
        this.col = col;
    }
    
    public int getCol() {
        return col;
    }
}
