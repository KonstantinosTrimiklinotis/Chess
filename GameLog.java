import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameLog {
    final private List<Move> gameLog = new ArrayList<>();
    final private Map<Cell, Boolean> hasMovedFrom = new HashMap<>();
    public void addMove(Move move){
        hasMovedFrom.put(move.getStart(), true);
        gameLog.add(move);
    }

    public List<Move> getLog(){
        return gameLog;
    }

    public boolean isLastMove(Move move){
        if (gameLog.isEmpty()){
            return false;
        }
        return gameLog.get(gameLog.size() - 1).equals(move);
    }

    public boolean hasMovedFrom(Cell cell){
        if (hasMovedFrom.get(cell) == null){
            return false;
        }
        return hasMovedFrom.get(cell);
    }


}
