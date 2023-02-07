import java.util.List;

public class Board {
    private final Piece[][] board;

    private Cell whiteKingPos;
    private Cell blackKingPos;

    BoardFiller filler;

    public boolean cellIsEmpty(Cell cell){
        return board[cell.row()][cell.col()] == null;
    }
    public Piece getPiece(Cell cell){
        return board[cell.row()][cell.col()];
    }

    public boolean checkKingsThread(Cell kingPos, Color oponentColor){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Cell curPos = new Cell(i, j);
                Piece curPiece = getPiece(curPos);
                if (curPiece != null
                        && curPiece.getColor() == oponentColor){
                    List<Cell> attacked =
                            curPiece.getAttackedCells(curPos, this, null);
                    if (attacked.contains(kingPos)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkKingsThread(Color oponentColor){
        Cell kingPos = (
                oponentColor == Color.white ?
                        blackKingPos : whiteKingPos
                );
        return checkKingsThread(kingPos, oponentColor);
    }

    private static class ClassicFiller extends BoardFiller{

        @Override
        protected void fillRooks(Piece[][] board) {
            startWhiteLeftRook = new Cell(0, 0);
            startWhiteRightRook = new Cell(0, 7);
            startBlackLeftRook = new Cell(7, 0);
            startBlackRightRook = new Cell(7, 7);
            board[0][0] = new Rook(Color.white);
            board[0][7] = new Rook(Color.white);
            board[7][0] = new Rook(Color.black);
            board[7][7] = new Rook(Color.black);
        }

        @Override
        protected void fillKnights(Piece[][] board) {
            board[0][1] = new Knight(Color.white);
            board[0][6] = new Knight(Color.white);
            board[7][1] = new Knight(Color.black);
            board[7][6] = new Knight(Color.black);
        }

        @Override
        protected void fillBishops(Piece[][] board) {
            board[0][2] = new Bishop(Color.white);
            board[0][5] = new Bishop(Color.white);
            board[7][2] = new Bishop(Color.black);
            board[7][5] = new Bishop(Color.black);
        }

        @Override
        protected void fillQueens(Piece[][] board) {
            board[0][3] = new Queen(Color.white);
            board[7][3] = new Queen(Color.black);
        }

        @Override
        protected void fillKings(Piece[][] board) {
            startWhiteKingPos = new Cell(0, 4);
            startBlackKingPos = new Cell(7, 4);
            board[0][4] = new King(Color.white);
            board[7][4] = new King(Color.black);
        }
    }

    public Board(GameType gameType){
        this.board = new Piece[8][8];
        if (gameType == GameType.classic){
            filler = new ClassicFiller();
            filler.fillPieces(board);
            whiteKingPos = filler.startWhiteKingPos;
            blackKingPos = filler.startBlackKingPos;
        }
    }

    private Board(Piece[][] board){
        this.board = board;
    }

    public Board makeMove(Move move){
        Piece[][] newBoard = board;
        Piece piece = newBoard[move.start().row()][move.start().col()];
        newBoard[move.start().row()][move.start().col()] = null;
        newBoard[move.finish().row()][move.finish().col()] = piece;
        return new Board(newBoard);
    }

    public List<Cell> getStartCellsWhiteRooks(){
        return filler.getStartCellsWhiteRooks();
    }

    public List<Cell> getStartCellsBlackRooks(){
        return filler.getStartCellsBlackRooks();
    }

    public Cell getStartCellWhiteKings(){
        return filler.getStartCellWhiteKings();
    }

    public Cell getStartCellBlackKings(){
        return filler.getStartCellBlackKings();
    }
}
