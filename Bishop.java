import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{
    Bishop(Color color) {
        super(color);
    }

    private List<Cell> upperLeftDiagonal(Cell start, Board board){
        ArrayList<Cell> moves = new ArrayList<>();
        for (int i = start.row() + 1, j = start.col() - 1;
            i < 8 && j >= 0;
            i++, j--){
            Cell curCell = new Cell(i, j);
            moves.add(curCell);
            if (!board.cellIsEmpty(curCell)){
                break;
            }
        }
        return moves;
    }

    private List<Cell> upperRightDiagonal(Cell start, Board board){
        ArrayList<Cell> moves = new ArrayList<>();
        for (int i = start.row() + 1, j = start.col() + 1;
            i < 8 && j < 8;
            i++, j++){
            Cell curCell = new Cell(i, j);
            moves.add(curCell);
            if (!board.cellIsEmpty(curCell)){
                break;
            }
        }
        return moves;
    }

    private List<Cell> bottomLeftDiagonal(Cell start, Board board){
        ArrayList<Cell> moves = new ArrayList<>();
        for (int i = start.row() - 1, j = start.col() - 1;
            i >= 0 && j >= 0;
            i--, j--){
            Cell curCell = new Cell(i, j);
            moves.add(curCell);
            if (!board.cellIsEmpty(curCell)){
                break;
            }
        }
        return moves;
    }

    private List<Cell> bottomRightDiagonal(Cell start, Board board){
        ArrayList<Cell> moves = new ArrayList<>();
        for (int i = start.row() - 1, j = start.col() + 1;
            i >= 0 && j < 8;
            i--, j++){
            Cell curCell = new Cell(i, j);
            moves.add(curCell);
            if (!board.cellIsEmpty(curCell)){
                break;
            }
        }
        return moves;
    }
    @Override
    public List<Cell> getMoves(Cell start, Board board) {
        ArrayList<Cell> moves = new ArrayList<>();
        moves.addAll(upperLeftDiagonal(start, board));
        moves.addAll(upperRightDiagonal(start, board));
        moves.addAll(bottomLeftDiagonal(start, board));
        moves.addAll(bottomRightDiagonal(start, board));
        return moves;
    }

    @Override
    public boolean checkMove(Cell start, Cell finish, Board board) {
        return getMoves(start, board).contains(finish);
    }
}
