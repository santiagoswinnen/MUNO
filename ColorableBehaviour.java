public class ColorableBehaviour implements Behaviour{
	private Game game;
	ColorableBehaviour(Game game){
		this.game = game;
	}
	public void action(String...args){
		game.getDealer().lastCard().setColor(args[0]);
	}
}
