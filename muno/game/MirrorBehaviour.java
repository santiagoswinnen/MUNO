package muno.game;
import java.io.Serializable;

/** Behaviour of the Mirror Cards*/
public class MirrorBehaviour implements Behaviour, Serializable {
    private UNOGame game;
    
    public MirrorBehaviour(UNOGame game) {
        this.game = game;
    }

    public void action(String...args) {

        game.getDealer().lastCard().setColor(args[0]);
        game.reverseGameFLow();
        game.getNextPlayer();
        game.getNextPlayer();
        if(game.getDealer().getLog().isActionCard() && !game.getDealer().getLog().getName().equals("Mirror")) {
            if(game.getDealer().getLog().getName().equals("Reverse"))
                game.getNextPlayer();
            ((ActionCard)game.getDealer().getLog()).makeAction();
        }
    }
    
}