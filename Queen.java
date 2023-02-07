import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    final private Rook rook;
    final private Bishop bishop;

    Queen(Color color) {
        super(color);
        rook = new Rook(color);
        bishop = new Bishop(color);
    }

    @Override
    public List<Move> getMoves(Cell start, Board board, GameLog gameLog) {
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(rook.getMoves(start, board, null));
        moves.addAll(bishop.getMoves(start, board, null));
        moves.removeIf(move -> dangerousForKingMove(move, board));
        return moves;
    }
}
