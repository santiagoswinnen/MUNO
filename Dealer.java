package muno.game;

import java.io.Serializable;

/**
 *Class that represents the dealer of the game that controls the flow of the cards
 *
 */
public class Dealer implements Serializable{
    //Listado de jugadores
    private UNOGame game;
    private DrawPile drawPile;

    //Descarte. Se crea vacio
    // Contiene el mazo de cartas
    private DiscardPile discardPile;
    /*carta para log de cartas en el mazo de descarte*/
    private Card log;

    
    /**Creates a draw pile and a discard pile. 
	 *Creates a new dealer
	 *@param game Reference to the game.
     */
    public Dealer(UNOGame game){
        this.game = game;
    }

    /**Empties a player's hand.*/
    public void askForCards(){
        for (Player p: this.game.getPlayers()){
            p.emptyHand();
        }
    }
    
    //falta comentar
    public Card getLog(){
        return log;
    }

    /**Ask the players for the cards. 
     *Creates a draw pile and a discard pile.
     *Deals seven cards to each player.
     *Sets the first card in the discard pile.
     *@deprecated a new DrawPile and DiscardPile are created 
     *  because deal must be called in case of the start of a new round.
     */
    public void deal (){
        askForCards();
        drawPile = new DrawPile(game);
        discardPile = new DiscardPile();
        for(int x = 0 ; x < this.game.getPlayers().size(); x++){
            for(int y = 0; y < 7; y++){
            	this.game.getPlayers().get(x).addCard(drawCard());
            }
        }
        setFirstCard();
        
    }

   /**Adds a card to the discard pile.
	*
	*@param card The card to be added.
	*/
    public void discardCard(Card card){
        if(!discardPile.isEmpty())
            log = lastCard();
        discardPile.throwCard(card);
        //DECIA ADDCARD, PUSIMOS THROWCARD
    }

   /**Puts the Discard Pile's cards into the Draw Pile*/
    private void setDecks(){
        drawPile.setDrawPile(discardPile.askCards());
    }

   /**If the draw pile is empty then puts the cards from the DrawPile. 
	*   Anyway gets a card.
	*
	*@return a card from the DrawPile. 
	*/
    public Card drawCard() {
        if (drawPile.isEmpty())
            setDecks();
        return drawPile.getCard();
    }

   /**Returns the last card in the DiscardPile*/
    public Card lastCard(){
        return discardPile.lastCard();
    }
    
    /**Sets the first card in the DiscardPile*/
    public void setFirstCard(){
    	Card cardAux = drawCard();
    	while(cardAux.isActionCard()){
    		discardCard(cardAux);
    		cardAux = drawCard();
    	}
    	discardCard(cardAux);	
    }




}
