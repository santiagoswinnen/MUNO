package muno.game;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
/**
 * Created by lmikolas on 08/06/17.
 */
public class Leaderboard {
    private Map<Player, Integer> scoreboard = new HashMap<Player, Integer>();
    private UNOGame game;

    Leaderboard(UNOGame game) {
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

    public String toString() {
	String ret = " | ";
	for(Player player : scoreboard.keySet()) {
            ret += player.getName() + " score: "+ scoreboard.get(player) + " | ";
        }
	return ret;
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
	/*actualiza los puntajes después de cada ronda*/
    public void updateScores() {
        Player winner = getRoundWinner();
        for(Player player : scoreboard.keySet()) {
            for(Card card : player.getHand()){
                    addScore(winner, card.getScore());
            }
        }
        if(hasWinner()){
            game.endGame();
        }
    }
    public Player getRoundWinner(){
        if(game.getCurrentPlayer().getHand().size() == 0){
            return game.getCurrentPlayer();
        }
        throw new UnsupportedOperationException("no round winner");
    }

    public void addScore(Player player,Integer score){
        scoreboard.put(player, (scoreboard.get(player) + score));
    }

    /*devuelve el score del jugador pasado como argumento*/
    public Integer getScore(Player player) {
        return scoreboard.get(player);
    }


}
