package muno.game;

import java.io.Serializable;

/**
 * Created by lmikolas on 10/06/17.
 */
public class DrawNBehaviour implements Behaviour, Serializable{
    private UNOGame game;
    private Integer number;


    DrawNBehaviour(UNOGame game, Integer number){
        this.game = game;
        this.number = number;

    }
    public void action(String...args) {
        for(int i = 0; i < number; i++){
            
            game.getCurrentPlayer().addCard(game.getDealer().drawCard());
        }
        /* Si se trata de un DrawFour recibe como parametro un String color */
        if(args.length == 1){
            game.getDealer().lastCard().setColor(args[0]);
        }
        game.getNextPlayer();
    }



}
