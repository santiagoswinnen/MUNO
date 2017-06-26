package muno.game;

import java.io.Serializable;

/**
 * Created by lmikolas on 07/06/17.
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
