package game;

import java.util.Random;

/**
 *
 * @author Luis Felipe Quesada
 */
public class Board {
    
    private int[][] matrix, res = null;
    
    public Board(int[][] matrix, int level) {
        this.matrix = matrix;
        this.res = matrix;
        setEmptySpaces(level);
    }
    
    public int[][] getUserBoard() {
        return res;
    }
    
    public int[][] getCompleteBoard() {
        return matrix;
    }
    
    private void setEmptySpaces(int level) {
        switch(level) {
            case 0:
                // 0 - 35
                for(int counter = 0; counter < 25; counter++) {
                    res[generateNumber()][generateNumber()] = 0;
                }
                break;
            case 1:
                // 36 - 50
                for(int counter = 0; counter < 40; counter++) {
                    res[generateNumber()][generateNumber()] = 0;
                }
                break;
            case 2:
                // 51 - 63
                for(int counter = 0; counter < 55; counter++) {
                    res[generateNumber()][generateNumber()] = 0;
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
}


