package muno.game;

import java.io.Serializable;

/**
 * Class that represents an action card.
 */
public class ActionCard extends Card implements Serializable {
	
    private Behaviour behaviour;
   
   /** Creates a new action card.
    *
    *  @param name Card's name.
    *  @param score Card's score.
    *  @param color Card's color.
    *  @param behaviour Card's behavior.
    */
    public ActionCard(String name, Integer score, String color, Behaviour behaviour) {
        super(name, score, color);
        this.behaviour = behaviour;
    }

    public void makeAction(String ... args) {
            behaviour.action(args);
    }
    
   /** Checks if two action cards match
    *
    *  @param card The card to compare with.
    *  @return true if the cards match.
    */
    public boolean match(Card card) {
        if(getColor().equals("black") ||  getColor().equals(card.getColor()) || getName().equals(card.getName()))
            return true;
        return false;
    }
    
}