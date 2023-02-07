import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    King(Color color) {
        super(color);
    }

    private List<Cell> crossMoves(Cell start){
        List<Cell> moves = new ArrayList<>();
        Cell forwardCell = new Cell(start.row() + 1, start.col());
        if (start.row() < 7){
            moves.add(forwardCell);
        }
        Cell leftCell = new Cell(start.row(), start.col() - 1);
        if (start.col() > 0){
            moves.add(leftCell);
        }
        Cell rightCell = new Cell(start.row(), start.col() + 1);
        if (start.col() < 7){
            moves.add(rightCell);
        }
        Cell backwordCell = new Cell(start.row() - 1, start.col());
        if (start.row() > 0){
            moves.add(backwordCell);
        }
        return moves;
    }

    private List<Cell> diagonalMoves(Cell start){
        List<Cell> moves = new ArrayList<>();
        Cell leftForward = new Cell(start.row() + 1, start.col() - 1);
        if (start.row() < 7 && start.col() > 0){
            moves.add(leftForward);
        }
        Cell rightForward = new Cell(start.row() + 1, start.col() + 1);
        if (start.row() < 7 && start.col() < 7){
            moves.add(rightForward);
        }
        Cell leftBackward = new Cell(start.row() - 1, start.col() - 1);
        if (start.row() > 0 && start.col() > 0){
            moves.add(leftBackward);
        }
        Cell rightBackward = new Cell(start.row() - 1, start.col() + 1);
        if (start.row() > 0 && start.col() < 7){
            moves.add(rightBackward);
        }
        return moves;
    }

    private boolean checkKingsSafetyCastling(Cell start,
                                             Board board,
                                             boolean longCastle){
        Color color = board.getPiece(start).getColor();
        Color opColor = (color == Color.white ?
                Color.black : Color.white);
        Cell[] cells = new Cell[2];
        if (longCastle){
            cells[0] = new Cell(start.row(), start.col() - 1);
            cells[1] = new Cell(start.row(), start.col() - 2);
        }
        else{
            cells[0] = new Cell(start.row(), start.col() + 1);
            cells[1] = new Cell(start.row(), start.col() + 2);
        }
        return board.checkKingsThread(cells[0], opColor)
                && board.checkKingsThread(cells[1], opColor);
    }

    private List<Move> castle(Cell start, Board board, GameLog gameLog){
        List<Move> moves = new ArrayList<>();
        Color color = board.getPiece(start).getColor();
        Cell king = (color == Color.white ?
                board.getStartCellWhiteKings() : board.getStartCellBlackKings());
        if (gameLog.hasMovedFrom(king)){
            return moves;
        }
        List<Cell> rooks = (color == Color.white ?
                board.getStartCellsWhiteRooks() : board.getStartCellsBlackRooks());
        if (!gameLog.hasMovedFrom(rooks.get(0))
                && checkKingsSafetyCastling(start, board, true)){
            Cell kingLeftMove = new Cell(start.row(), start.col() - 2);
            moves.add(new Move(start, kingLeftMove));
        }
        if (!gameLog.hasMovedFrom(rooks.get(1))
                && checkKingsSafetyCastling(start, board, false)){
            Cell kingRightMove = new Cell(start.row(), start.col() + 2);
            moves.add(new Move(start, kingRightMove));
        }
        return moves;
    }

    @Override
    public List<Cell> getAttackedCells(Cell start, Board board, GameLog gameLog){
        ArrayList<Cell> moves = new ArrayList<>();
        moves.addAll(crossMoves(start));
        moves.addAll(diagonalMoves(start));
        return moves;
    }

    @Override
    public List<Move> getMoves(Cell start, Board board, GameLog gameLog) {
        List<Cell> attackedCells = getAttackedCells(start, board, gameLog);
        Color opponentColor = (
                board.getPiece(start).getColor() == Color.white
                        ? Color.black : Color.white);
        attackedCells.removeIf(cell -> board.checkKingsThread(cell, opponentColor));
        List<Move> moves = new ArrayList<>();
        for (Cell attackedCell : attackedCells){
            moves.add(new Move(start, attackedCell));
        }
        moves.addAll(castle(start, board, gameLog));
        return moves;
    }
}
