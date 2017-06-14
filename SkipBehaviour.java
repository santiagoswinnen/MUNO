/**
 * Created by lmikolas on 07/06/17.
 */
public class SkipBehaviour implements Behaviour{
    Game game;

    SkipBehaviour(Game game) {
        this.game = game;
    }
    public void action(String... args) {
        game.getNextPlayer();
    }
}
