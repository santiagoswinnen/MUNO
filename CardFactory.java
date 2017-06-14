class CardFactory() {
	public static Card getCard(String color, String name,Game game) {
		Card card;
		switch(name){
			case "Zero":
				return card = new NumberedCard(name,0,color);
				Break;
			case "One":
				card = new NumberedCard(name,1,color);
				Break;
			case "Two":
				card= new NumberedCard(name,2,color);
				Break;
			case "Three":
				card = new NumberedCard(name,3,color);
				Break;
			case "Four":
				card = new NumberedCard(name,4,color);
				Break;
			case "Five":
				card = new NumberedCard(name,5,color);
				Break;
			case "Six":
				card = new NumberedCard(name,6,color);
				Break;
			case "Seven":
				card = new NumberedCard(name,7,color);
				Break;
			case "Eight":
				card = new NumberedCard(name,8,color);
				Break;
			case "Nine":
				card = new NumberedCard(name,9,color);
				Break;
			case "DrawTwo":
				card = new ActionCard(name, 20, color, new DrawNCardsBehaviour(game,2));
				Break;
			case "Skip":
				card = new ActionCard(name, 20, color, new SkipBehaviour(game)); 
				Break;
			case "Reverse":
				card = new ActionCard(name, 20, color, new ReverseBehaviour(game));
				Break;
			case "Wild":
				card = new ActionCard(name, 50, color, new ColorableBehaviour(game));
				Break;
			case "Mirror":
				card = new ActionCard(name, 50, color, new ColorableBehaviour(game));
				Break;
			case "DrawFour":
				card = new ActionCard(name, 50, color, new DrawNCardBehaviour(game,4));
				Break;
			default: throw new IllegalArgumentException("Wrong card name");
			}
			
		return card; 
		} 
	}
}
