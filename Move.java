public class Move {
    protected Cell start;
    protected Cell finish;

    public Cell getStart() {
        return start;
    }

    public Cell getFinish() {
        return finish;
    }

    public Move(Cell start, Cell finish){
        this.start = start;
        this.finish = finish;
    }

    @Override
    public boolean equals(Object a){
        if (a instanceof Move){
            Move move = (Move)(a);
            return this.getStart().equals(move.getStart())
                    && this.getFinish().equals(move.getFinish());
        }
        return false;
    }


}
