package muno.game;

import java.io.Serializable;

/**
 * Behaviour to reverse the flow of the game
 */
public class ReverseBehaviour implements Behaviour, Serializable {
	
    private UNOGame game;

    public ReverseBehaviour(UNOGame game) {
        this.game = game;
    }
    
    public void action(String... args) {
        game.reverseGameFLow();
        game.getNextPlayer();
        game.getNextPlayer();
    }
    
}