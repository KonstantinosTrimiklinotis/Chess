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

    @Override
    public List<Cell> getAttackedCells(Cell start, Board board){
        ArrayList<Cell> moves = new ArrayList<>();
        moves.addAll(crossMoves(start));
        moves.addAll(diagonalMoves(start));
        return moves;
    }

    @Override
    public List<Move> getMoves(Cell start, Board board) {
        List<Cell> attackedCells = getAttackedCells(start, board);
        Color opponentColor = (
                board.getPiece(start).getColor() == Color.white
                        ? Color.black : Color.white);
        attackedCells.removeIf(cell -> board.checkKingsThread(cell, opponentColor));
        List<Move> moves = new ArrayList<>();
        for (Cell attackedCell : attackedCells){
            moves.add(new Move(start, attackedCell));
        }
        return moves;
    }
}
