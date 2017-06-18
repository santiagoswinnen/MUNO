package muno.game;

/**
 * Created by lmikolas on 16/06/17.
 */
public class MirrorBehaviour implements Behaviour {
    private UNOGame game;
    MirrorBehaviour(UNOGame game){
        this.game = game;
    }

    public void action(String...args){

        game.getDealer().lastCard().setColor(args[0]);
        game.reverseGameFLow();
        game.getNextPlayer();
        game.getNextPlayer();
        if(game.getDealer().getLog().isActionCard() && !game.getDealer().getLog().getName().equals("Mirror")){
                ((ActionCard)game.getDealer().getLog()).makeAction();
        }
    }
}


