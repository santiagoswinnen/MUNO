package muno.game;

/**
 * @author jlococo 
 */
public class Dealer {
    //Listado de jugadores
    private UNOGame game;

    //Extraccion
    private DrawPile drawPile;

    //Descarte. Se crea vacio
    // Contiene el mazo de cartas
    private DiscardPile discardPile;
    /*carta para log de cartas en el mazo de descarte*/
    private Card log;

    // Crea un mazo principal y de descarte.
    // Crea un nuevo Dealer.
    public Dealer(UNOGame game){
        this.game = game;
    }

    /*vacia las manos de los jugadores*/
    public void askForCards(){
        for (Player p: this.game.getPlayers()){
            p.emptyHand();
        }
    }
    public Card getLog(){
        return log;
    }

    //Reparte a los jugadores las cartas.
    //Antes pide a los jugadores las cartas.
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

    // Agrega una carta al mazo de Descarte.
    public void discardCard(Card card){
        discardPile.throwCard(card);
        log = card;//DECIA ADDCARD, PUSIMOS THROWCARD
    }

    //Coloca las cartas del mazo de descarte, en el mazo principal
    private void setDecks(){
        drawPile.setDrawPile(discardPile.askCards());
    }

    //Si el mazo principal esta vacio,
    //transpasa las cartas del mazo de descarte
    //al mazo principal.
    //De todos modos, toma una carta.
    public Card drawCard() {
        if (drawPile.isEmpty())
            setDecks();
        return drawPile.getCard();
    }

    // Retorna la ultima carta del mazo de descarte
    public Card lastCard(){
        return discardPile.lastCard();
    }
    
    /* Setea la 1ra carta del DiscardPile */
    public void setFirstCard(){
    	Card cardAux = drawCard();
    	while(cardAux.isActionCard()){
    		discardCard(cardAux);
    		cardAux = drawCard();
    	}
    	discardCard(cardAux);	
    }


}
