package muno.game;

/**
 * Created by lmikolas on 07/06/17.
 */
public class ReverseBehaviour implements Behaviour {
    private UNOGame game;

    ReverseBehaviour(UNOGame game) {
        this.game = game;
    }
    public void action(String... args) {
        game.reverseGameFLow();
        game.getNextPlayer();
    }
}
