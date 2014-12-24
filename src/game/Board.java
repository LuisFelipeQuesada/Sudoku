package game;

/**
 *
 * @author Luis Felipe Quesada
 */
public class Board {
    
    private int numRows = 3;
    private int numCols = 3;
    private Block[][] board = null; 
    
    public Board() {
        board = new Block[numRows][numCols];
    }
    
    public Block[][] getBoard() {
        return board;
    }
    
    public int getNumRows() {
        return numRows;
    }
    
    public int getNumCols() {
        return numCols;
    }
    
    public void addToBoard(Block block) {
        
    }
}
