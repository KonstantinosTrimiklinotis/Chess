import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    Pawn(Color color) {
        super(color);
    }

    @Override
    public List<Cell> getAttackedCells(Cell pos, Board board, GameLog gameLog){
        List<Move> moves = takeMove(pos, board, false, gameLog);
        List<Cell> attackedCells = new ArrayList<>();
        for (Move move : moves){
            attackedCells.add(move.getFinish());
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

    private boolean enPassantPossibility(Cell start,
                                              Board board, boolean left,
                                              GameLog gameLog){
        Color ourColor = board.getPiece(start).getColor();
        int enPassantRow = (ourColor == Color.white ? 4 : 3);
        if (start.row() != enPassantRow){
            return false;
        }
        int col = (left ? start.col() - 1 : start.col() + 1);
        int opRow = (ourColor == Color.white ?
                start.row() + 2 : start.row() - 2);
        Cell adjacent = new Cell(start.row(), col);
        Cell opPrev = new Cell(opRow, col);

        return (board.getPiece(adjacent) instanceof Pawn
            && gameLog.isLastMove(new Move(opPrev, adjacent)));
    }

    private List<Move> takeMove(Cell start,
                              Board board, boolean isMove, GameLog gameLog){
        ArrayList<Move> moves = new ArrayList<>();
        int forwardRow = (color == Color.white ?
                start.row() + 1 : start.row() - 1);

        if (start.col() > 0){
            Cell finish = new Cell(forwardRow, start.col() - 1);
            if (isMove){
                if(!board.cellIsEmpty(finish)
                        || enPassantPossibility(start, board, true, gameLog)){
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
                        new Cell(forwardRow, start.col() + 1))
                        || enPassantPossibility(start, board, false, gameLog)){
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
    public List<Move> getMoves(Cell start, Board board, GameLog gameLog) {
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(forwardMove(start, board));
        moves.addAll(takeMove(start, board, true, gameLog));
        moves.removeIf(move -> dangerousForKingMove(move, board));
        return moves;
    }
}
