public class Board {
    final private Piece[][] board = new Piece[8][8];

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
                    // bug when we go in pawn attacked cell
                }
            }
        }
    }

    private static class ClassicFiller extends BoardFiller{

        @Override
        protected void fillRooks(Piece[][] board) {
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
            board[0][4] = new King(Color.white);
            board[7][4] = new King(Color.black);
        }
    }

    public Board(GameType gameType){
        if (gameType == GameType.classic){
            ClassicFiller filler = new ClassicFiller();
            filler.fillPieces(board);
        }
    }
}
