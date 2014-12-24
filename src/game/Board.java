package game;

/**
 *
 * @author Luis Felipe Quesada
 */
public class Board {
    
    private int numRows = 3;
    private int numCols = 3;
    private int[][] matrix, res = null;
    
    public Board(int[][] matrix) {
        this.matrix = matrix;
        this.res = matrix;
    }
    
    public int getNumRows() {
        return numRows;
    }
    
    public int getNumCols() {
        return numCols;
    }
    
    public int[][] getBoard() {
        return matrix;
    }
}
