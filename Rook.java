import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{
    Rook(Color color) {
        super(color);
    }

    private List<Cell> forwardMoves(Cell start, Board board){
        ArrayList<Cell> moves = new ArrayList<>();
        for (int i = start.row() + 1; i < 8; i++){
            Cell curCell = new Cell(i, start.col());
            moves.add(curCell);
            if (!board.cellIsEmpty(curCell)){
                break;
            }
        }
        return moves;
    }

    private List<Cell> backwardMoves(Cell start, Board board){
        ArrayList<Cell> moves = new ArrayList<>();
        for (int i = start.row() - 1; i >= 0; i--){
            Cell curCell = new Cell(i, start.col());
            moves.add(curCell);
            if (!board.cellIsEmpty(curCell)){
                break;
            }
        }
        return moves;
    }

    private List<Cell> leftwardMoves(Cell start, Board board){
        ArrayList<Cell> moves = new ArrayList<>();
        for (int i = start.col() - 1; i >= 0; i--){
            Cell curCell = new Cell(start.row(), i);
            moves.add(curCell);
            if (!board.cellIsEmpty(curCell)){
                break;
            }
        }
        return moves;
    }

    private List<Cell> rightwardMoves(Cell start, Board board){
        ArrayList<Cell> moves = new ArrayList<>();
        for (int i = start.col() + 1; i < 8; i++){
            Cell curCell = new Cell(start.row(), i);
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
        moves.addAll(forwardMoves(start, board));
        moves.addAll(backwardMoves(start, board));
        moves.addAll(leftwardMoves(start, board));
        moves.addAll(rightwardMoves(start, board));
        return moves;
    }

    @Override
    public boolean checkMove(Cell start, Cell finish, Board board) {
        return getMoves(start, board).contains(finish);
    }
}
