package muno.game;

import java.util.ArrayList;

/**
 * Created by lmikolas on 07/06/17.
 */
public class Player {
    private ArrayList<Card> hand;
    private UNOGame game;
    private String name;
    

    public Player(){};

    public Player(String name, UNOGame game) {

    	hand = new ArrayList<Card>();
    	this.game = game;
        this.name = name;
        
        
    }
    /*Pregunta si le queda una sola carta al jugador*/
    public boolean hasUNO(){
        if(hand.size() == 1)
            return true;
        return false;
    }

    public String getName(){
        return name;
    }

    /*agrega carta a la mano*/
    public void addCard(Card card) {
        this.hand.add(card);
    }


    /*el jugador entrega su mano y su mano queda vacía*/
    public void emptyHand() {
       hand.clear();
    }
    
    /*muestra la mano el juador, pero se la queda*/
     public ArrayList<Card> getHand() {
        return hand;
    }

    /*el jugador tira su carta en el mazo de descarte*/
    public boolean throwCard(Card card) {
        int i = hand.indexOf(card);
        if(i == -1)
            throw new IllegalArgumentException("No such card in hand");
        if(card.match(game.getDealer().lastCard())) {
            game.getDealer().discardCard(hand.remove(i));
            return true;
        }
        return false;
    }

    /*verifico que el jugador este en el juego*/
    public boolean checkGame(){
        if(game.getPlayers().contains(this)){
            return true;
        }
        return false;
    }
    
    public UNOGame getGame(){
    	return this.game;
    }


    public String toString(){
        return getName();
    }

}
