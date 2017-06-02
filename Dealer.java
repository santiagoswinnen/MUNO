public class Dealer{
	private ArrayList<Player> players;

	private ArrayList<Card> cardDeckD;
	//Descarte. Se crea vacio

	private ArrayList<Card> cardDeckE;
	//Extraccion
	// Contiene el mazo de cartas

	public Dealer(ArrayList<Player> players){
		this.players=players;
		ArrayList<Card> cardDeckE=new ArrayList<Card>;
		ArrayList<Card> cardDeckD=new ArrayList<Card>;
	}

	// Crea un nuevo Dealer.
	// Crea un mazo de cartas y lo llena. 

	public void shuffle(){
		Collections.shuffle(cardDeckE);	
	} 
	
	// Sortea las cartas del dealer

	public void deal (){
		//AskForCards()
		// for(int x = 0 ; x<players.size();x++){
		// 	players.get(x).pedircartas();	
		// }		

		newDeck();

		for(int x = 0 ; x<players.size();x++){
			for (int y = 0; y < 7; y++ ){
				players.get(x).addCard(getCard());
			}	
		}


	}
	//Reparte a los jugadores las cartas.
	

	public Card getCard() {
		if (isEmpty()){
			setDecks();
		}
		return cardDeckE.remove(cardDeck.size()-1);

	}
	//Si el mazo principal esta vacio, llama a SetDecks() quien pasa,
	//las cartas del mazo de descarte al mazo Principal y las mezcla. 
	//Saca una carta del CardDeck. 
	
	public boolean isEmpty(){
		return cardDeckE.size() == 0; 
	}
	//devuelve size del mazo principal==0

	public int size(){
		return cardDeckE.size();
	}

	// Retorna el tamaño del mazo de cartas.

	public void throwCard(Card card){
		// if (!isEmpty() && lastCard().instanceof(WildCard)){
			//lastcard().setColor=blacK
		//}
		cardDeckD.add(card);	
	}
	// Agrega una carta al mazo de Descarte. 
	// Una vez que una WildCard (Carta color negro) deja de estar primera, le cambia el color a negro. 

	public void setDecks(){
		Card aux = lastCard();
		for(int r = 0 ; r < cardDeckD.size(); r++ ){
			cardDeckE.add(cardDeckD.remove(r)); 
		}
		cardDeckD.clear();
		cardDeckD.add(aux);
	}
	//Coloca las cartas del mazo de descarte, que no sean la primera
	//en el mazo principal y lo mezcla.  

	public Card lastCard(){
		if (!isEmpty()){
			return cardDeckD.get(size()-1)
		}
	}
	
	//Devuelve la primer carta del mazo de descarte. 

	public void newDeck() {
		// Crea el mazo de cartas


		shuffle();
	}
}