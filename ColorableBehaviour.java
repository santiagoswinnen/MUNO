package muno.game;

public class ColorableBehaviour implements Behaviour{
	private UNOGame game;
	ColorableBehaviour(UNOGame game){
		this.game = game;
	}
	public void action(String...args){
		game.getDealer().lastCard().setColor(args[0]);
	}
}
