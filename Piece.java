import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected Color color;

    public List<Cell> getAttackedCells(Cell pos, Board board){
        List<Cell> cells = new ArrayList<>();
        for (Move move : getMoves(pos, board)){
            cells.add(move.finish());
        }
        return cells;
    }

    abstract public List<Move> getMoves(Cell start, Board board);
    public boolean checkMove(Move move,
                             Board board){
        return getMoves(move.start(), board).contains(move);
    }
    Piece(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean dangerousForKingMove(Move move, Board board){
        Board afterMoveBoard = board.makeMove(move);
        Color piecesColor = board.getPiece(move.start()).getColor();
        return afterMoveBoard.checkKingsThread(piecesColor);
    }
}
