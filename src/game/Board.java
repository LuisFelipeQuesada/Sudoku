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
    private int row, col, data, emptySpaces = 0;
    private boolean validationResult = false;
    private boolean is_valid, is_won = false;
    
    public Board(int[][] m, int level) {
        emptySpaces = 0;
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
                    emptySpaces += 1;
                }
                break;
            case 1:
                // 36 - 50
                for(int counter = 0; counter < 40; counter++) {
                    toUser[generateNumber()][generateNumber()] = 0;
                    emptySpaces += 1;
                }
                break;
            case 2:
                // 51 - 63
                for(int counter = 0; counter < 55; counter++) {
                    toUser[generateNumber()][generateNumber()] = 0;
                    emptySpaces += 1;
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
        row = r;
        col = c;
    }
    
    public boolean validateInput(int row, int col, int d) {
        boolean isValid = false;
        if(matrix[row][col] == d) {
            validationResult = true;
            TextFieldFocusListener.res = true;
            toUser[row][col] = d;
            isValid = true;
            is_valid = isValid;
        }
        return isValid;
    }
    
    public boolean isWon() {
        boolean isWon = true;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                if(matrix[i][j] == toUser[i][j]) {
                    ;
                }
                else {
                    isWon = false;
                    j += matrix.length;
                    i += matrix.length;
                }
            }
        }
        return isWon;
    }
    
    public boolean noEmptySpaces() {
        return (emptySpaces == 0);
    }
    
    private int countEmptySpaces() {
        int spaces = 0;
        for(int i = 0; i < toUser.length; i++) {
            for(int j = 0; j < toUser[i].length; j++) {
                if(toUser[i][j] == 0) {
                    spaces += 1;
                }
            }
        }
        return spaces;
    }
    
    public boolean getIsValid() {
        return is_valid;
    }
    
    public boolean getIsWon() {
        return is_won;
    }
}


