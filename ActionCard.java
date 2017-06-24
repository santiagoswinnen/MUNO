package muno.game;

import java.io.Serializable;

/**
 * Created by lmikolas on 07/06/17.
 */
public class ActionCard extends Card implements Serializable {
    private Behaviour behaviour;
    ActionCard(String name, Integer score, String color, Behaviour behaviour){
        super(name, score, color);
        this.behaviour = behaviour;
    }

    public void makeAction(String ... args) {
            behaviour.action(args);
           
    }

    public boolean match(Card card){
        if(this.getColor().equals("black") ||  this.getColor().equals(card.getColor()) || this.getName().equals(card.getName())){
            return true;
        }
        return false;
    }
}
