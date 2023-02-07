import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    Pawn(Color color) {
        super(color);
    }

    @Override
    public List<Cell> getAttackedCells(Cell pos, Board board){
        List<Move> moves = takeMove(pos, board, false);
        List<Cell> attackedCells = new ArrayList<>();
        for (Move move : moves){
            attackedCells.add(move.finish());
        }
        return attackedCells;
    }

    private List<Move> forwardMove(Cell start,
                                   Board board){
        int forwardRow = (color == Color.white ?
                start.row() + 1 : start.row() - 1);
        Cell forwardCell = new Cell(forwardRow, start.col());
        return (board.cellIsEmpty(forwardCell) ?
            List.of(new Move(start, forwardCell)) : List.of()
        );
    }

    private List<Move> takeMove(Cell start,
                              Board board, boolean isMove){
        ArrayList<Move> moves = new ArrayList<>();
        int forwardRow = (color == Color.white ?
                start.row() + 1 : start.row() - 1);

        if (start.col() > 0){
            Cell finish = new Cell(forwardRow, start.col() - 1);
            if (isMove){
                if(!board.cellIsEmpty(finish)){
                    moves.add(new Move(start, finish));
                }
            }
            else{
                moves.add(new Move(start, finish));
            }
        }
        if (start.col() < 7){
            Cell finish = new Cell(forwardRow, start.col() + 1);
            if (isMove){
                if (!board.cellIsEmpty(
                        new Cell(forwardRow, start.col() + 1))){
                    moves.add(new Move(start, finish));
                }
            }
            else{
                moves.add(new Move(start, finish));
            }
        }
        return moves;
    }

    @Override
    public List<Move> getMoves(Cell start, Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(forwardMove(start, board));
        moves.addAll(takeMove(start, board, true));
        moves.removeIf(move -> dangerousForKingMove(move, board));
        return moves;
    }
}
