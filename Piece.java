import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected Color color;

    public List<Cell> getAttackedCells(Cell pos, Board board, GameLog gameLog){
        List<Cell> cells = new ArrayList<>();
        for (Move move : getMoves(pos, board, gameLog)){
            cells.add(move.getFinish());
        }
        return cells;
    }

    abstract public List<Move> getMoves(Cell start, Board board, GameLog gameLog);
    public boolean checkMove(Move move,
                             Board board, GameLog gameLog){
        return getMoves(move.getStart(), board, gameLog).contains(move);
    }
    Piece(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean dangerousForKingMove(Move move, Board board){
        Board afterMoveBoard = board.makeMove(move);
        Color piecesColor = board.getPiece(move.getStart()).getColor();
        return afterMoveBoard.checkKingsThread(piecesColor);
    }
}
