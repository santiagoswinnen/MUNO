package muno.game;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author JLoCoco
 */
public class DiscardPile implements Serializable {
    private ArrayList<Card> discardPile;

    public DiscardPile(){
        discardPile = new ArrayList<Card>();
    }

    public Card lastCard(){
        return getDiscardPile().get(getDiscardPile().size() - 1);
    }

    public void throwCard(Card card){
        if (!isEmpty() && lastCard().isWildCard()){
            lastCard().setColor("black");
        }
        getDiscardPile().add(card);
    }
    
    private ArrayList<Card> getDiscardPile(){
        return this.discardPile;
    }
    
    private int size(){
        return getDiscardPile().size();
    }

    // Una vez que una WildCard (Carta color negro) deja de estar primera, le cambia el color a negro.
    public ArrayList<Card> askCards(){
        Card cardAux= lastCard();
        ArrayList <Card> aux = new ArrayList<Card>();
        for (int t = 0 ; t < size() - 2; t++ ) {
            aux.add(getDiscardPile().remove(t));
        }
        getDiscardPile().clear();
        throwCard(cardAux);
        return aux;
    }
    
    public boolean isEmpty(){
    	return this.discardPile.size() == 0;
    }

}
