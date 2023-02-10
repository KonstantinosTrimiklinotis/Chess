public class Game {

    GameType type = GameType.classic;
    Board board = new Board(type);

    Player player1 = new RealPlayer();
    Player player2 = new RealPlayer();

    public int play(){

    }
}
