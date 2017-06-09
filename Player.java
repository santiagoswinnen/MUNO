import java.util.ArrayList;

/**
 * Created by lmikolas on 07/06/17.
 */
public class Player {
    private ArrayList<Card> hand;
    private Game game;
    private String name;

    Player(){};

    Player(String name, Game game) {
        if(!checkGame())
            throw new IllegalArgumentException("Player is not in the game");
        this.game = game;
        this.name = name;
        hand = new ArrayList<>();
    }
    

    /*agrega carta a la mano*/
    public void addCard(Card card) {
        hand.add(card);
    }


    /*el jugador entrega su mano y su mano queda vacía*/
    public ArrayList<Card> giveHand() {
        ArrayList<Card> aux = hand;
        hand = null;
        return aux;
    }
    
    /*muestra la mano el juador, pero se la queda*/
     public ArrayList<Card> getHand() {
        return hand;
    }

    /*el jugador tira su carta en el mazo de descarte*/
    public void throwCard(Card card) {
        int i = hand.indexOf(card);
        if(i == -1)
            throw new IllegalArgumentException("No such card in hand");
        if(card.match(game.getDealer().lastCard())) {
            game.getDealer().discardCard(hand.remove(i));
        }
    }

    /*verifico que el jugador este en el juego*/
    public boolean checkGame(){
        if(game.getPlayers().contains(this)){
            return true;
        }
        return false;
    }
}
