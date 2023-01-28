import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{
    final private Rook rook;
    final private Bishop bishop;

    Queen(Color color) {
        super(color);
        rook = new Rook(color);
        bishop = new Bishop(color);
    }

    @Override
    public List<Cell> getMoves(Cell start, Board board) {
        ArrayList<Cell> moves = new ArrayList<>();
        moves.addAll(rook.getMoves(start, board));
        moves.addAll(bishop.getMoves(start, board));
        return moves;
    }

    @Override
    public boolean checkMove(Cell start, Cell finish, Board board) {
        return getMoves(start, board).contains(finish);
    }
}
