/**
 * Created by Lo Coco on 16/06/2017.
 */
package muno.game;

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
        if (size()==0)
            throw new IndexOutOfBoundsException;
        return getDiscardPile().get(size() - 1);
    }

    /**
     * Agrega la carta al mazo de descarte.
     * Si la ultima carta era wildcard, le retorna su color a "black"
     * @param card Carta a agregar al mazo.
     */
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

    /**
     *
     * @return Un array list de Cards, con las cartas
     *          que se encontraban en el DiscardPile menos
     *          la ultima carta.
     */
    public ArrayList<Card> askCards(){
        Card cardAux= lastCard();
        ArrayList <Card> aux = new ArrayList<Card>();
        for (int t = 0 ; t < size() - 2; t++ ) {
            aux.add(getDiscardPile().remove(t));
        }
        getDiscardPile().clear();
        getDiscardPile().add(cardAux);
        return aux;
    }

    public boolean isEmpty(){
        return this.discardPile.size() == 0;
    }

}
