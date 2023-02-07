import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

    Knight(Color color) {
        super(color);
    }

    private List<Move> forwardMoves(Cell start){
        ArrayList<Move> moves = new ArrayList<>();
        if (start.row() < 6){
            if (start.col() > 0){
                Cell finish = new Cell(
                        start.row() + 2, start.col() - 1
                );
                moves.add(new Move(start, finish));
            }
            if (start.col() < 7){
                Cell finish = new Cell(
                        start.row() + 2, start.col() + 1
                );
                moves.add(new Move(start, finish));
            }
        }
        return moves;
    }

    private List<Move> backwardMoves(Cell start){
        ArrayList<Move> moves = new ArrayList<>();
        if (start.row() > 1){
            if (start.col() > 0){
                Cell finish = new Cell(
                        start.row() - 2, start.col() - 1
                );
                moves.add(new Move(start, finish));
            }
            if (start.row() < 7){
                Cell finish = new Cell(
                        start.row() - 2, start.col() + 1
                );
                moves.add(new Move(start, finish));
            }
        }
        return moves;
    }

    private List<Move> leftwardMoves(Cell start){
        ArrayList<Move> moves = new ArrayList<>();
        if (start.col() > 1){
            if (start.row() > 0){
                Cell finish = new Cell(
                        start.row() - 1, start.col() - 2
                );
                moves.add(new Move(start, finish));
            }
            if (start.row() < 7){
                Cell finish = new Cell(
                        start.row() + 1, start.col() - 2
                );
                moves.add(new Move(start, finish));
            }
        }
        return moves;
    }

    private List<Move> rightwardMoves(Cell start){
        ArrayList<Move> moves = new ArrayList<>();
        if (start.col() < 6){
            if (start.row() > 0){
                Cell finish = new Cell(
                        start.row() - 1, start.col() + 2
                );
                moves.add(new Move(start, finish));
            }
            if (start.row() < 7){
                Cell finish = new Cell(
                        start.row() + 1, start.col() + 2
                );
                moves.add(new Move(start, finish));
            }
        }
        return moves;
    }

    @Override
    public List<Move> getMoves(Cell start, Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(forwardMoves(start));
        moves.addAll(backwardMoves(start));
        moves.addAll(leftwardMoves(start));
        moves.addAll(rightwardMoves(start));
        moves.removeIf(move -> dangerousForKingMove(move, board));
        return moves;
    }
}
