package muno.game;

import java.io.Serializable;

/**
 * @author jlococo 
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

    
     /**
     * Se crea un nuevo Dealer
     * @param game Referencia a Game .
     */
    public Dealer(UNOGame game){
        this.game = game;
    }

    /**
     *vacia las manos de los jugadores
     */
    public void askForCards(){
        for (Player p: this.game.getPlayers()){
            p.emptyHand();
        }
    }
    public Card getLog(){
        return log;
    }

    /**
     * Pide las cartas a los demas jugadores.
     * Crea un mazo de descarte y un mazo para agarrar cartas
     * Reparte 7 cartas a cada jugador de la partida.
     * Por ultimo setea la primer carta en el mazo de descarte.
     * @deprecated se crean nuevo DrawPile y nuevo DiscardPile
     *      debido a que deal debe llamarse en caso de que se
     *      quiera comenzar una nueva ronda.
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

    /**
     * Agrega una carta al mazo de Descarte.
     * @param card Carta a agregar al mazo de descarte
     */
    public void discardCard(Card card){
        if(!discardPile.isEmpty())
            log = lastCard();
        discardPile.throwCard(card);
        //DECIA ADDCARD, PUSIMOS THROWCARD
    }

   /**
     *Coloca las cartas del mazo de descarte, en el mazo principal
     */
    private void setDecks(){
        drawPile.setDrawPile(discardPile.askCards());
    }

   /**
     *Si el mazo principal esta vacio,
     *transpasa las cartas del mazo de descarte
     *al mazo principal.
     *
     *  @return una carta tomada del DrawPile
     */
    public Card drawCard() {
        if (drawPile.isEmpty())
            setDecks();
        return drawPile.getCard();
    }

    /**
     *@return Card ultima carta del mazo de descarte
     */
    public Card lastCard(){
        return discardPile.lastCard();
    }
    
    /**
     * Setea la primer carta en el mazo Principal.
     */
    public void setFirstCard(){
    	Card cardAux = drawCard();
    	while(cardAux.isActionCard()){
    		discardCard(cardAux);
    		cardAux = drawCard();
    	}
    	discardCard(cardAux);	
    }




}
