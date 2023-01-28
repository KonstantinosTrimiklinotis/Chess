import java.util.List;

public abstract class Piece {
    protected Color color;

    abstract public List<Cell> getMoves(Cell start, Board board);
    abstract public boolean checkMove(Cell start, Cell finish,
                             Board board);
    Piece(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
