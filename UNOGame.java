package muno.game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class UNOGame {

	private ArrayList<Player> players;
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
	private class GameFlowIterator {
		private Iterator<Player> iterator;
		private Player currentPlayer;
		private ArrayList<Player> players;

		GameFlowIterator(ArrayList<Player> players){
			this.players = new ArrayList<Player>();
			this.players.addAll(players);
			iterator = players.iterator();
		}

		public Player next(){
			if (!iterator.hasNext()){
				iterator = players.iterator();
			}
			currentPlayer = iterator.next();
			return currentPlayer; 
		}

		public Player getcurrentPlayer(){
			return currentPlayer;
		}

		public void reverse(){
			ArrayList<Player> aux = new ArrayList<Player>();
			int k = 0;
			aux.add(currentPlayer);
			while(k < players.size() - 1){
				if(!iterator.hasNext())
					iterator = players.iterator();
				aux.add(iterator.next());
				k++;
			}
			Collections.reverse(aux);
			players = aux;
			iterator = players.iterator();
		}

		public boolean hasNext() {
			return true;
		}
	}
}
