import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{
    Bishop(Color color) {
        super(color);
    }

    private List<Move> upperLeftDiagonal(Cell start, Board board){
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = start.row() + 1, j = start.col() - 1;
            i < 8 && j >= 0;
            i++, j--){
            Cell curCell = new Cell(i, j);
            moves.add(new Move(start, curCell));
            if (!board.cellIsEmpty(curCell)){
                break;
            }
        }
        return moves;
    }

    private List<Move> upperRightDiagonal(Cell start, Board board){
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = start.row() + 1, j = start.col() + 1;
            i < 8 && j < 8;
            i++, j++){
            Cell curCell = new Cell(i, j);
            moves.add(new Move(start, curCell));
            if (!board.cellIsEmpty(curCell)){
                break;
            }
        }
        return moves;
    }

    private List<Move> bottomLeftDiagonal(Cell start, Board board){
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = start.row() - 1, j = start.col() - 1;
            i >= 0 && j >= 0;
            i--, j--){
            Cell curCell = new Cell(i, j);
            moves.add(new Move(start, curCell));
            if (!board.cellIsEmpty(curCell)){
                break;
            }
        }
        return moves;
    }

    private List<Move> bottomRightDiagonal(Cell start, Board board){
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = start.row() - 1, j = start.col() + 1;
            i >= 0 && j < 8;
            i--, j++){
            Cell curCell = new Cell(i, j);
            moves.add(new Move(start, curCell));
            if (!board.cellIsEmpty(curCell)){
                break;
            }
        }
        return moves;
    }

    @Override
    public List<Move> getMoves(Cell start, Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(upperLeftDiagonal(start, board));
        moves.addAll(upperRightDiagonal(start, board));
        moves.addAll(bottomLeftDiagonal(start, board));
        moves.addAll(bottomRightDiagonal(start, board));
        moves.removeIf(move -> dangerousForKingMove(move, board));
        return moves;
    }
}
