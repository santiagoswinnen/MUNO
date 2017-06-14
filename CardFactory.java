package muno.game;

public class CardFactory {
	public static Card getCard(String color, String name, UNOGame game){
		Card card;
		
		if(name.equals("Zero"))
			return card = new NumberedCard(name, 0, color);
		if(name.equals("One"))
			return card = new NumberedCard(name, 1, color);
		if(name.equals("Two"))
			return card = new NumberedCard(name, 2, color);
		if(name.equals("Three"))
			return card = new NumberedCard(name, 3, color);
		if(name.equals("Four"))
			return card = new NumberedCard(name, 4, color);
		if(name.equals("Five"))
			return card = new NumberedCard(name, 5, color);
		if(name.equals("Six"))
			return card = new NumberedCard(name, 6, color);
		if(name.equals("Seven"))
			return card = new NumberedCard(name, 7, color);
		if(name.equals("Eight"))
			return card = new NumberedCard(name, 8, color);
		if(name.equals("Nine"))
			return card = new NumberedCard(name, 9, color);
		if(name.equals("DrawTwo"))
			return card = new ActionCard(name, 20, color, new DrawNBehaviour(game,2));
		if(name.equals("Skip"))
			return card = new ActionCard(name, 20, color, new SkipBehaviour(game)); 
		if(name.equals("Reverse"))
			return card = new ActionCard(name, 20, color, new ReverseBehaviour(game));
		if(name.equals("Wild"))
			return card = new ActionCard(name, 50, color, new ColorableBehaviour(game));
		if(name.equals("Mirror"))
			return card = new ActionCard(name, 50, color, new ColorableBehaviour(game));
		if(name.equals("DrawFour"))
			return card = new ActionCard(name, 50, color, new DrawNBehaviour(game,4));
		throw new IllegalArgumentException("Wrong card name");
	}
}
