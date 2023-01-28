public abstract class BoardFiller {
    protected void fillPawns(Piece[][] board){
        for (int i = 0; i < 8; i++){
            board[1][i] = new Pawn(Color.white);
            board[6][i] = new Pawn(Color.black);
        }
    }

    protected abstract void fillRooks(Piece[][] board);
    protected abstract void fillKnights(Piece[][] board);
    protected abstract void fillBishops(Piece[][] board);
    protected abstract void fillQueens(Piece[][] board);
    protected abstract void fillKings(Piece[][] board);

    public void fillPieces(Piece[][] board){
        fillPawns(board);
        fillRooks(board);
        fillKnights(board);
        fillBishops(board);
        fillQueens(board);
        fillKings(board);
    }

}
