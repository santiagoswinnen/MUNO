package muno.game;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
/**
 * Leaderboard of the game: contains the scores of the players.
 */
public class Leaderboard implements Serializable {
    private Map<Player, Integer> scoreboard = new HashMap<Player, Integer>();
    private UNOGame game;
    /**
     *Constructor method of the class Leaderboard. Initializes the players' scores in 0.
     *
     *@param game The UNOgame that contains the players.
     */
    Leaderboard(UNOGame game) {
        this.game = game;
        for(Player player : game.getPlayers()) {
            scoreboard.put(player, 0);
        }
    }
  
   /**Verifies if there is a winner by checking the scores.
    *
    *@return true if there is a winner, false if not.
    */
    public boolean hasWinner(){
        for(Integer score : scoreboard.values()){
            if(score >= 500){
                return true;
            }
        }
        return false;
    }
    
    //
    public String toString() {
	String ret = " | ";
	for(Player player : scoreboard.keySet()) {
            ret += player.getName() + " score: "+ scoreboard.get(player) + " | ";
        }
	return ret;
      }

    /**Returns the winner.
     *
     *@throws NoSuchElementException if hasWinner returns false (there's not a winner yet).
     *@return player The player that won the game.
     */
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

    /** Updates the scores after each round. */
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
	
    /**Gets the winner of the round
     *
     *@throws UnsupportedOperationException if there is not a round winner.
     */
    public Player getRoundWinner(){
        if(game.getCurrentPlayer().getHand().size() == 0){
            return game.getCurrentPlayer();
        }
        throw new UnsupportedOperationException("no round winner");
    }

    public void addScore(Player player,Integer score){
        scoreboard.put(player, (scoreboard.get(player) + score));
    }

    /**Returns the score of the player in the parameter*/
    public Integer getScore(Player player) {
        return scoreboard.get(player);
    }
    
    public Integer getScore(int index) {
    	return scoreboard.get(game.getPlayers().get(index));
    }
}
