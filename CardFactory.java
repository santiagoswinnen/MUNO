class CardFactory(){
	public static Card getCard(String color, String name){
		Card card;
		switch(name){
			case "Zero":
				card= new NumberedCard(name,0,color);
				Break;
			case "One":
				card= new NumberedCard(name,1,color);
				Break;
			case "Two":
				card= new NumberedCard(name,2,color);
				Break;
			case "Three":
				card= new NumberedCard(name,3,color);
				Break;
			case "Four":
				card= new NumberedCard(name,4,color);
				Break;
			case "Five":
				card= new NumberedCard(name,5,color);
				Break;
			case "Six":
				card= new NumberedCard(name,6,color);
				Break;
			case "Seven":
				card= new NumberedCard(name,7,color);
				Break;
			case "Eight":
				card= new NumberedCard(name,8,color);
				Break;
			case "Nine":
				card= new NumberedCard(name,9,color);
				Break;
			case "DrawTwo":
				//Sentencia1;
				Break;
			case "Skip":
				//Sentencia1;
				Break;
			case "Reverse":
				//Sentencia1;
				Break;
			case "Wild":
				//Sentencia1;
				Break;
			case "Mirror":
				//Sentencia1;
				Break;
			case "DrawFour":
				//Sentencia1;
				Break;
			}
		return card; 
		} 
	}
}