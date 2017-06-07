/**
 * Created by lmikolas on 07/06/17.
 */
public class DrawTwoBehaviour implements Behaviour {
    Game game;

    DrawTwoBehaviour(Game game) {
        this.game=game;
    }

    public void action() {
        for(int i = 0; i < 2 ; i++)
            game.getCurrentPlayer().addCard(game.getDealer().getCard());
    }
}
