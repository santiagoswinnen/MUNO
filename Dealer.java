import java.util.ArrayList;

/**
 * @author jlococo 
 */
public class Dealer {
    //Listado de jugadores
    private ArrayList<Player> players;
    private Game game;

    //Extraccion
    private DrawPile drawPile;

    //Descarte. Se crea vacio
    // Contiene el mazo de cartas
    private DiscardPile discardPile;

    // Crea un mazo principal y de descarte.
    // Crea un nuevo Dealer.
    public Dealer(Game game){
        this.game = game
        this.players=game.getPlayers();    
    }

    /*vacia las manos de los jugadores*/
    public void AskForCards(){
        for (Player p: players){
            p.giveHand();
        }
    }

    //Reparte a los jugadores las cartas.
    //Antes pide a los jugadores las cartas.
    public void deal (){
        AskForCards();
        drawPile = new DrawPile(game);
        discardPile = new DiscardPile();
        for(int x = 0 ; x<players.size();x++){
            for (int y = 0; y < 7; y++ ){
                players.get(x).addCard(drawCard());
            }
        }
    }

    // Agrega una carta al mazo de Descarte.
    public void discardCard(Card card){
        discardPile.addCard(card);
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
        drawPile.getCard();
    }

    // Retorna la ultima carta del mazo de descarte
    public Card lastCard(){
        return discardPile.lastCard();
    }
}
