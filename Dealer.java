/**
 * Created by Lo Coco on 02/06/2017.
 */
public class Dealer {
    //Listado de jugadores
    private ArrayList<Player> players;

    //Extraccion
    private DrawPile drawPile;

    //Descarte. Se crea vacio
    // Contiene el mazo de cartas
    private DiscardPile discardPile;

    // Crea un mazo principal y de descarte.
    // Crea un nuevo Dealer.
    public Dealer(ArrayList<Player> players){
        this.players=players;
        drawPile = new DrawPile();
        discardPile = new DiscardPile();
    }



    //Reparte a los jugadores las cartas.
    //Antes pide a los jugadores las cartas.
    public void deal (){
        //AskForCards()
        // for(int x = 0 ; x<players.size();x++){
        // 	players.get(x).pedircartas();
        // }

        for(int x = 0 ; x<players.size();x++){
            for (int y = 0; y < 7; y++ ){
                players.get(x).addCard(getCard());
            }
        }
    }

    // Agrega una carta al mazo de Descarte.
    public void throwCard(Card card){
        discardPile.throwCard(card);
    }

    //Coloca las cartas del mazo de descarte, en el mazo principal
    private void setDecks(){
        drawPile.setDrawPile(discardPile.askCards());
    }

    //Si el mazo principal esta vacio,
    //transpasa las cartas del mazo de descarte
    //al mazo principal.
    //De todos modos, toma una carta.
    public Card getCard() {
        if (drawPile.isEmpty())
            setDecks();
        drawPile.getCard();
    }

    // Retorna la ultima carta del mazo de descarte
    public Card lastCard(){
        return discardPile.lastCard();
    }
}
