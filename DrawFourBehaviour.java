/**
 * Created by lmikolas on 07/06/17.
 */
public class DrawFourBehaviour implements Behaviour {
    Game game;

    DrawFourBehaviour(Game game) {
        this.game=game;
    }
    /*Esta accion se va ejecutar cuando sea el turno del siguiente */
    public void action() {
        for(int i = 0; i < 4 ; i++)
            game.getCurrentPlayer().addCard(game.getDealer().getCard());

    }


}
