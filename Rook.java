import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{
    Rook(Color color) {
        super(color);
    }

    private List<Move> forwardMoves(Cell start, Board board){
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = start.row() + 1; i < 8; i++){
            Cell curCell = new Cell(i, start.col());
            moves.add(new Move(start, curCell));
            if (!board.cellIsEmpty(curCell)){
                break;
            }
        }
        return moves;
    }

    private List<Move> backwardMoves(Cell start, Board board){
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = start.row() - 1; i >= 0; i--){
            Cell curCell = new Cell(i, start.col());
            moves.add(new Move(start, curCell));
            if (!board.cellIsEmpty(curCell)){
                break;
            }
        }
        return moves;
    }

    private List<Move> leftwardMoves(Cell start, Board board){
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = start.col() - 1; i >= 0; i--){
            Cell curCell = new Cell(start.row(), i);
            moves.add(new Move(start, curCell));
            if (!board.cellIsEmpty(curCell)){
                break;
            }
        }
        return moves;
    }

    private List<Move> rightwardMoves(Cell start, Board board){
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = start.col() + 1; i < 8; i++){
            Cell curCell = new Cell(start.row(), i);
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
        moves.addAll(forwardMoves(start, board));
        moves.addAll(backwardMoves(start, board));
        moves.addAll(leftwardMoves(start, board));
        moves.addAll(rightwardMoves(start, board));
        moves.removeIf(move -> dangerousForKingMove(move, board));
        return moves;
    }
}
