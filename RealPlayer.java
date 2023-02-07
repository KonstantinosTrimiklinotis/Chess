import java.util.List;
import java.util.Scanner;

public class RealPlayer implements Player{

    final static private char FIRST_COLUMN = 'A';
    final static private String rules = """
            How to play:
             Every turn enter cell with chosen piece and destination cell, put space between them and use format: A1 or a1, with first column char and second number of row
            """;
    final static private String makeMoveInvitation = "Enter your move pls";
    final static private String inputErrorNotification = "Wrong format of input";



    public void printRules(){
        System.out.println(rules);
    }

    public void inviteForMove(){
        System.out.println(makeMoveInvitation);
    }

    public void printInputErrorNotification(){
        System.out.println(inputErrorNotification);
    }

    public int columnConverterCharToInt(char col){
        return col - FIRST_COLUMN;
    }

    private Cell parser(){
        int row;
        int col;
        Scanner in = new Scanner(System.in);
        String str = (in.next()).toUpperCase();
        if (str.length() != 2
                || !Character.isAlphabetic(str.charAt(0))
                || !Character.isDigit(str.charAt(1))){
            return null;
        }
        row = Character.digit(str.charAt(1), 10);
        col = columnConverterCharToInt(str.charAt(0));
        if (col > 7){
            return null;
        }
        return new Cell(row, col);
    }

    private Move inputParser(){
        Cell start = parser();
        Cell finish = parser();
        if (start == null || finish == null){
            return null;
        }
        return new Move(start, finish);
    }
    @Override
    public Move makeMove(Board board) {
        inviteForMove();
        Move move = inputParser();
        while (move == null){
            printInputErrorNotification();
            printRules();
            inviteForMove();
            move = inputParser();
        }
        return move;
    }
}
