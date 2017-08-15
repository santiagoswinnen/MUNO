package muno.game;

import java.io.Serializable;

/**
 * Behaviour that draws a specific number of cards
 */
public class DrawNBehaviour implements Behaviour, Serializable {
	
    private UNOGame game;
    private Integer number;

    /** Draws a number of cards.
     * @param game Reference to the game
     * @param number Number of cards to draw
     */
    public DrawNBehaviour(UNOGame game, Integer number) {
        this.game = game;
        this.number = number;
    }
    
    public void action(String...args) {
        for(int i = 0; i < number; i++) {
            game.getCurrentPlayer().addCard(game.getDealer().drawCard());
        }
        
        /* If the card is DrawFour receives a String color parameter */
        if(args.length == 1)
            game.getDealer().lastCard().setColor(args[0]);
            
        game.getNextPlayer();
    }
    
}