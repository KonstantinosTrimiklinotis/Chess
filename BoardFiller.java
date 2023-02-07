import java.util.List;

public abstract class BoardFiller {

    protected Cell startWhiteKingPos;
    protected Cell startBlackKingPos;

    protected Cell startWhiteLeftRook;
    protected Cell startWhiteRightRook;
    protected Cell startBlackLeftRook;
    protected Cell startBlackRightRook;


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

    public List<Cell> getStartCellsWhiteRooks(){
        return List.of(startWhiteLeftRook, startWhiteRightRook);
    }

    public List<Cell> getStartCellsBlackRooks(){
        return List.of(startBlackLeftRook, startBlackRightRook);
    }

    public Cell getStartCellWhiteKings(){
        return startWhiteKingPos;
    }

    public Cell getStartCellBlackKings(){
        return startBlackKingPos;
    }

}
