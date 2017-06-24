package muno.game;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class UNOGame implements Serializable{

	private ArrayList<Player> players = new ArrayList<Player>();
	private Dealer dealer;
	private GameFlowIterator flow;
	private Leaderboard leaderboard;

	/*Estado del round y del juego en su totalidad respectivamente, SE VA A DISCUTIR EN EL FUTURO*/
	private boolean gameState;

	public UNOGame(){
		players = new ArrayList<Player>();
		dealer = new Dealer(this);
		gameState = true;
	}
	
	public Dealer getDealer(){
		return dealer;
	}
	
	public Leaderboard getLeaderboard(){
		return leaderboard;
	}

	public Player getNextPlayer(){
		return flow.next();
	}

	public void reverseGameFLow(){
		flow.reverse();
	}

	public Player getCurrentPlayer(){
		return flow.getcurrentPlayer();
	}

	/*Setea los lugares de los jugadores para el Round de manera random*/
//	public void setPlayerPosition() {
//		Collections.shuffle(players);
//		flow = new GameFlowIterator();
//	}

	public ArrayList<Player> getPlayers(){
		return players;
	}

	/*Devuelve si el juego termino o no*/
	public boolean getGameState(){
		return gameState;
	}

	public void endGame(){
		gameState=false;
	}

	public void addPlayers(ArrayList<Player> players){
		if(this.players.size() != 0)
			throw new UnsupportedOperationException("Players have already been added");
		Iterator<Player> iterator = players.iterator();
		while(iterator.hasNext())
			this.players.add(iterator.next());
		flow = new GameFlowIterator(players);
		leaderboard = new Leaderboard(this);
	}

	/*iterator que maneja el flujo del juego*/
	private class GameFlowIterator implements Serializable {
		private int index = -1;
		private int orientation = 1;
		private Player currentPlayer;
		private ArrayList<Player> players;

		GameFlowIterator(ArrayList<Player> players){
			this.players = new ArrayList<Player>();
			this.players.addAll(players);
		}

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

		public Player getcurrentPlayer(){
			return currentPlayer;
		}

		public void reverse(){
			orientation = orientation*(-1);
		}

		public boolean hasNext() {
			return true;
		}
	}
}
