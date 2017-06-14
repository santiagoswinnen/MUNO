package muno.game;

/**
 * Created by lmikolas on 07/06/17.
 */
public class SkipBehaviour implements Behaviour{
    private UNOGame game;

    SkipBehaviour(UNOGame game) {
        this.game = game;
    }
    public void action(String... args) {
        game.getNextPlayer();
    }
}
