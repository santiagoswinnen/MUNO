/**
 * Created by lmikolas on 07/06/17.
 */
public class ReverseBehaviour implements Behaviour {
    Game game;

    ReverseBehaviour(Game game) {
        this.game = game;
    }
    public void action(String... args) {
        game.reverseGameFLow();
        game.getNextPlayer();
    }
}
