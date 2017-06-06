import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Game {

	private Leaderboard leaderboard;
	private ArrayList<Player> players = new ArrayList<>(); //controla el flujo del juego
	private Dealer dealer = new Dealer(players);
	private GameFlowIterator flow;



	/*Estado del round y del juego en su totalidad respectivamente, SE VA A DISCUTIR EN EL FUTURO*/
	private boolean UNO;
	private boolean gameState;



	public Game( Dealer dealer) {
		this.dealer = dealer;
		gameState = true;
		
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
	public void setPlayerPosition(){
		Collections.shuffle(players);
		flow = new GameFlowIterator();
	}





	public ArrayList<Player> getPlayers() {

		return players;
	}




	/*Devuelve si el juego termino o no*/
	public boolean gameState() {

		return gameState;
	}

	public void endGame() {
		gameState=false;
	}


	/*Devuelve si algun jugador consiguio UNO!*/
	public boolean isUNO() {

		return UNO;
	}


	public void setUNO(boolean b) {

		UNO = true;
	}






	/*iterator que maneja el flujo del juego*/
	private class GameFlowIterator {
		Iterator<Player> iterator;
		Player currentPlayer;

		GameFlowIterator() {

			iterator = players.iterator();
		}

		public Player next() {
			if (!iterator.hasNext()){
				iterator = players.iterator();
			}

			currentPlayer = iterator.next();
			return currentPlayer;
		}

		public Player getcurrentPlayer() {
			return currentPlayer;
		}



		public void reverse() {
			ArrayList<Player> aux = new ArrayList<>();
			int k =0;
			aux.add(currentPlayer);
			while(k < players.size()-1){
				aux.add(iterator.next());
				k++;
			}
			Collections.reverse(aux);
			players = aux;
			iterator = players.iterator();
		}

	}


}
