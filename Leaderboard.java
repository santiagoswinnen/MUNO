import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by lmikolas on 08/06/17.
 */
public class Leaderboard {
    private Map<Player, Integer> scoreboard = new HashMap<>();
    private Game game;

    Leaderboard(Game game) {
        this.game = game;
        for(Player player : game.getPlayers()) {
            scoreboard.put(player, 0);
        }
    }


    /*verifica si hay un ganador*/
    public boolean hasWinner(){
        for(Integer score : scoreboard.values()){
            if(score >= 500){
                return true;
            }
        }
        return false;
    }

    /*devuelve el ganador*/
    public Player getWinner(){
        if(!hasWinner()){
            throw new NoSuchElementException("no winner yet");
        }
        for(Player player : scoreboard.keySet()){
            if(scoreboard.get(player) >= 500)
                return player;
        }
        return null;
    }

    public void updateScores() {
        Player winner = getWinner();
        for(Player player : scoreboard.keySet()) {
            for(Card card : player.getHand()){
                if(card instanceof DrawTwo || card instanceof Skip
                    || card instanceof  Reverse || card instanceof  Mirror){
                    addScore(winner, 20);
                }
                else if(card instanceof DrawFour || card instanceof  Wild){
                    addScore(winner, 50);
                }
            }
        }
    }

    public void addScore(Player player,Integer score){
        scoreboard.put(player, (scoreboard.get(player) + score));
    }

    /*devuelve el score del jugador pasado como argumento*/
    public Integer getScore(Player player) {
        return scoreboard.get(player);
    }


}
