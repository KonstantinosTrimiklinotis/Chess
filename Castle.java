public abstract class Castle extends SpecialMove{
    Cell rookPos;

    public Castle(Cell start, Cell finish, Cell rookPos) {
        super(start, finish);
        this.rookPos = rookPos;
    }

    public Cell getRookPos() {
        return rookPos;
    }
}
