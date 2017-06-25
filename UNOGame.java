package muno.game;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**Class that controls the flow of the game*/
public class UNOGame implements Serializable{

	private ArrayList<Player> players = new ArrayList<Player>();
	private Dealer dealer;
	private GameFlowIterator flow;
	private Leaderboard leaderboard;

	private boolean gameState;

	/**Constructor method of the class. Creates a new Muno Game.*/
	public UNOGame(){
		players = new ArrayList<Player>();
		dealer = new Dealer(this);
		gameState = true;
	}
	
	/** Returns dealer*/
	public Dealer getDealer(){
		return dealer;
	}
	
	/** Returns leaderboard*/
	public Leaderboard getLeaderboard(){
		return leaderboard;
	}
	
	/**Returns the player that is playing next according to the round*/
	public Player getNextPlayer(){
		return flow.next();
	}
	
	/** Changes the flow of the game from one direction to the opposite,
         *changing the player's turns.
         */
	public void reverseGameFLow(){
		flow.reverse();
	}

	/**Returns the player that is currently playing, whose turn is*/
	public Player getCurrentPlayer(){
		return flow.getcurrentPlayer();
	}

	/**Sets each player's place for the round randomly*/
//	public void setPlayerPosition() {
//		Collections.shuffle(players);
//		flow = new GameFlowIterator();
//	}
	
	/**Returns the arraylist with the players*/
	public ArrayList<Player> getPlayers(){
		return players;
	}

	/**Returns the state of the game.
         *
	 *@return gameState Variable that is true if the game is still going on, 
         *false if it ended.
         */
	public boolean getGameState(){
		return gameState;
	}
	
	/** Ends the game by changing the state of gameState to false*/
	public void endGame(){
		gameState=false;
	}

	/**Adds the players.
         *
         *@param players ArrayList with all the players that will play.
         *
         *@throws UnsupportedOperationException if the ArrayList already has players.
         */
	public void addPlayers(ArrayList<Player> players){
		if(this.players.size() != 0)
			throw new UnsupportedOperationException("Players have already been added");
		Iterator<Player> iterator = players.iterator();
		while(iterator.hasNext())
			this.players.add(iterator.next());
		flow = new GameFlowIterator(players);
		leaderboard = new Leaderboard(this);
	}

	/**Iterator that controls the flow of the game*/
	private class GameFlowIterator implements Serializable {
		private int index = -1;
		private int orientation = 1;
		private Player currentPlayer;
		private ArrayList<Player> players;
		
		/**Creates a new GameFlowIterator*/
		GameFlowIterator(ArrayList<Player> players){
			this.players = new ArrayList<Player>();
			this.players.addAll(players);
		}
		
		/**Sets currentPlayer in the next player in the round*/
		public Player next(){
			index += 1*orientation;
			if(index == -1){
				index = 3;
			}
			if(index == 4){
				index = 0;
			}
			currentPlayer =  players.get(index);
			return currentPlayer;

		}
		
		/**Returns the current player*/
		public Player getcurrentPlayer(){
			return currentPlayer;
		}
		
		/**Changes the orientation of the round*/
		public void reverse(){
			orientation = orientation*(-1);
		}
		
		
		public boolean hasNext() {
			return true;
		}
	}
}
