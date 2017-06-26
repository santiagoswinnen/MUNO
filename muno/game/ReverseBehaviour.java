package muno.game;

import java.io.Serializable;

/**
 * Created by lmikolas on 07/06/17.
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
