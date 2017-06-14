package muno.game;

/**
 * Created by lmikolas on 10/06/17.
 */
public class DrawNBehaviour implements Behaviour {
    private UNOGame game;
    private Integer n;


    DrawNBehaviour(UNOGame game, Integer number){
        this.game = game;
        n= number;

    }
    public void action(String...args) {
        for(int i = 0; i < n; i++)
            game.getCurrentPlayer().addCard(game.getDealer().drawCard());
        if(args.length == 1){
            game.getDealer().lastCard().setColor(args[0]);
        }
    }


}
