package muno.game;

import java.io.Serializable;

public class ColorableBehaviour implements Behaviour, Serializable {
	private UNOGame game;
	ColorableBehaviour(UNOGame game){
		this.game = game;
	}
	public void action(String...args){
		if(args.length ==1)
			game.getDealer().lastCard().setColor(args[0]);
	}
}
