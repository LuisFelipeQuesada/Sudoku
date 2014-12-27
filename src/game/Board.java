package game;

import UI.TextFieldFocusListener;
import java.util.Random;

/**
 *
 * @author Luis Felipe Quesada
 */
public class Board {
    
    private int[][] matrix = null;
    private int[][] toUser = null;
    private int row, col, data = 0;
    private boolean validationResult = false;
    
    public Board(int[][] m, int level) {
        this.matrix = m;
        this.toUser = createToUserMatrix(m);
        setEmptySpaces(level);
    }
    
    private int[][] createToUserMatrix(int[][] m) {
        int[][] res = new int[m.length][m.length];
        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[i].length; j++) {
                res[i][j] = m[i][j];
            }
        }
        return res;
    }
    
    public int[][] getUserBoard() {
        return toUser;
    }
    
    public int[][] getCompleteBoard() {
        return matrix;
    }
    
    private void setEmptySpaces(int level) {
        switch(level) {
            case 0:
                // 0 - 35
                for(int counter = 0; counter < 25; counter++) {
                    toUser[generateNumber()][generateNumber()] = 0;
                }
                break;
            case 1:
                // 36 - 50
                for(int counter = 0; counter < 40; counter++) {
                    toUser[generateNumber()][generateNumber()] = 0;
                }
                break;
            case 2:
                // 51 - 63
                for(int counter = 0; counter < 55; counter++) {
                    toUser[generateNumber()][generateNumber()] = 0;
                }
                break;
        }
    }
    
    private int generateNumber() {
        int number = 0;
        Random randomNumber = new Random();
        number = (randomNumber.nextInt(9));
        return number;
    }
    
    public void receiveCell(int r, int c) {
        this.row = r;
        this.col = c;
        System.out.println("En Matrix : " + matrix[this.row][this.col]);
        System.out.println("En toUser : " + toUser[this.row][this.col]);
    }
    
    public void validateInput(int row, int col, int d) {
        System.out.println("Dato ingresado: " + d);
        int inMat = matrix[row][col];
        System.out.println("inMat: " + inMat);
        if(matrix[row][col] == d) {
            validationResult = true;
            TextFieldFocusListener.res = true;
        }
        System.out.println(validationResult);
    }
    
    public boolean getValidationResult() {
        return validationResult;
    }
    
    public void resetValidationResult() {
        validationResult = false;
    }
}


