import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

    Knight(Color color) {
        super(color);
    }

    private List<Cell> forwardMoves(Cell start){
        ArrayList<Cell> moves = new ArrayList<>();
        if (start.row() < 6){
            if (start.col() > 0){
                moves.add(new Cell(
                        start.row() + 2, start.col() - 1
                ));
            }
            if (start.col() < 7){
                moves.add(new Cell(
                        start.row() + 2, start.col() + 1
                ));
            }
        }
        return moves;
    }

    private List<Cell> backwardMoves(Cell start){
        ArrayList<Cell> moves = new ArrayList<>();
        if (start.row() > 1){
            if (start.col() > 0){
                moves.add(new Cell(
                        start.row() - 2, start.col() - 1
                ));
            }
            if (start.row() < 7){
                moves.add(new Cell(
                        start.row() - 2, start.col() + 1
                ));
            }
        }
        return moves;
    }

    private List<Cell> leftwardMoves(Cell start){
        ArrayList<Cell> moves = new ArrayList<>();
        if (start.col() > 1){
            if (start.row() > 0){
                moves.add(new Cell(
                        start.row() - 1, start.col() - 2
                ));
            }
            if (start.row() < 7){
                moves.add(new Cell(
                        start.row() + 1, start.col() - 2
                ));
            }
        }
        return moves;
    }

    private List<Cell> rightwardMoves(Cell start){
        ArrayList<Cell> moves = new ArrayList<>();
        if (start.col() < 6){
            if (start.row() > 0){
                moves.add(new Cell(
                        start.row() - 1, start.col() + 2
                ));
            }
            if (start.row() < 7){
                moves.add(new Cell(
                        start.row() + 1, start.col() + 2
                ));
            }
        }
        return moves;
    }

    @Override
    public List<Cell> getMoves(Cell start, Board board) {
        ArrayList<Cell> moves = new ArrayList<>();
        moves.addAll(forwardMoves(start));
        moves.addAll(backwardMoves(start));
        moves.addAll(leftwardMoves(start));
        moves.addAll(rightwardMoves(start));
        return moves;
    }

    @Override
    public boolean checkMove(Cell start, Cell finish, Board board) {
        return getMoves(start, board).contains(finish);
    }
}
