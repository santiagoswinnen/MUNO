package muno.game;

import java.io.Serializable;

/**
 * Behaviour to skip the game.
 */
public class SkipBehaviour implements Behaviour, Serializable {
	
    private UNOGame game;

    public SkipBehaviour(UNOGame game) {
        this.game = game;
    }
    public void action(String... args) {
        game.getNextPlayer();
    }
    
}