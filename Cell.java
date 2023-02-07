public record Cell(int row, int col) {
    @Override
    public boolean equals(Object a){
        if (a instanceof Cell){
            Cell cell = (Cell)(a);
            return cell.row() == this.row()
                    && cell.col() == this.col();
        }
        return false;
    }
}
