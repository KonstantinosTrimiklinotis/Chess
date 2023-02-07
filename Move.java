public record Move(Cell start, Cell finish) {
    @Override
    public boolean equals(Object a){
        if (a instanceof Move){
            Move move = (Move)(a);
            return this.start().equals(move.start())
                    && this.finish().equals(move.finish());
        }
        return false;
    }
}
