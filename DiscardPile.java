import java.util.ArrayList;

/**
 * @author JLoCoco
 */
public class DiscardPile {
    private ArrayList<Card> discardPile;

    public DiscardPile(){
        discardPile = new ArrayList<Card>();
    }

    public Card lastCard(){
        return discardPile.get(size()-1)
    }

    public void throwCard(Card card){
        if (!isEmpty() && lastCard().iswildCard()){
            lastcard().setColor="black";
        }
        discardPile.add(card);
    }

    private int size(){
        return discardPile.size();
    }

    // Una vez que una WildCard (Carta color negro) deja de estar primera, le cambia el color a negro.
    public ArrayList<Card> askCards(){
        Card cardAux= lastCard();
        ArrayList <Card> aux = new ArrayList<Card>();
        for (int t = 0 ; t < size() - 2; t++ ) {
            aux.add(discardPile.remove(t));
        }
        discardPile.clear();
        discardPile.add(cardAux);
        return aux;
    }

}
