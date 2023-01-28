import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    King(Color color) {
        super(color);
    }

    @Override
    public List<Cell> getMoves(Cell start, Board board) {
        ArrayList<Cell> moves = new ArrayList<>();
    }

    @Override
    public boolean checkMove(Cell start, Cell finish, Board board) {
        return false;
    }
}
