import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    Pawn(Color color) {
        super(color);
    }

    private List<Cell> forwardMove(Cell start,
                                   Board board){
        int forwardRow = (color == Color.white ?
                start.row() + 1 : start.row() - 1);
        Cell forwardCell = new Cell(forwardRow, start.col());
        return (board.cellIsEmpty(forwardCell) ?
            List.of(forwardCell) : List.of()
        );
    }

    private List<Cell> takeMove(Cell start,
                              Board board){
        ArrayList<Cell> moves = new ArrayList<>();
        int forwardRow = (color == Color.white ?
                start.row() + 1 : start.row() - 1);

        if (start.col() > 0
                && !board.cellIsEmpty(
                        new Cell(forwardRow, start.col() - 1))){
            moves.add(new Cell(forwardRow, start.col() - 1));
        }
        if (start.col() < 7
                && !board.cellIsEmpty(
                        new Cell(forwardRow, start.col() + 1))){
            moves.add(new Cell(forwardRow, start.col() + 1));
        }
        return moves;
    }

    @Override
    public List<Cell> getMoves(Cell start, Board board) {
        ArrayList<Cell> moves = new ArrayList<>();
        moves.addAll(forwardMove(start, board));
        moves.addAll(takeMove(start, board));
        return moves;
    }

    @Override
    public boolean checkMove(Cell start, Cell finish,
                             Board board){
        return getMoves(start, board).contains(finish);
    }
}
