package muno.game;

import java.io.Serializable;

/**
 * Class that represents a MUNO card.
 */
public abstract class Card implements Serializable{
    private String name;
    private Integer score;
    private String color;
    
    private static final String [] CARD_COLORS={"black", "red", "yellow", "green", "blue"};
    private static final String [] CARD_NAMES={"Zero", "One", "Two","Three","Four",
                                                "Five", "Six", "Seven","Eight", "Nine",
                                                    "DrawTwo","Reverse", "Skip", "Mirror","DrawFour"
                                                        , "Wild"};
    private static final Integer [] CARD_SCORES={0,1,2,3,4,5,6,7,8,9,20,50};
    
    /**Constructor of the class. Creates a new card.
	*
	*@param name Card's name.
	*@param score Score that card represents.
	*@param color Card's color.
	*@throws IllegalArgumentException if one of the parameters is invalid.
	*
	*/
    Card(String name, Integer score, String color) {
        if(!checkName(name))
            throw new IllegalArgumentException("not valid name");
        else if(!checkColor(color))
            throw new IllegalArgumentException("not valid score");
        else if(!checkScore(score))
            throw new IllegalArgumentException("not valid score");
        this.name = name;
        this.score = score;
        this.color = color;
    }
    
     /** Sets the color of a card.
	*
	*@param the color to set.
	*@throws UnsupportedOperationException if the card is a wild card and its color can't be changed.
    *@throws IllegalArgumentException if the color is invalid.
	*/
    public void setColor(String color){
        if(!isWildCard()) {
            throw new UnsupportedOperationException("can't change color to this card");
        }
        if(!checkColor(color)){
            throw new IllegalArgumentException("not a valid color");
        }
        this.color = color;
    }
    
    /**Gets the cards' names.*/
    public static String[] getCardNames(){
        return CARD_NAMES;
    }
    
    /**Gets the cards' colors*/
    public static String[] getCardColors(){
        return CARD_COLORS;
    }
    
    /**
    *Checks if a name is a valid card name.
    *@param name The name to check.
    */ 
    public boolean checkName(String name){
        for(String cardName : CARD_NAMES){
            if(cardName.equals(name))
                return true;
        }
        return false;
    }
    
    /**
    *Checks if a score is valid.
    *@param score The score to check.
    */ 
    public boolean checkScore(Integer score){
        for(Integer aScore : CARD_SCORES){
            if(score.equals(aScore))
                return true;
        }
        return false;
    }
    
    /**
    Checks if a color is a valid card color.
    *@param color The color to check.
    */ 
    public static boolean checkColor(String color){
        for(String aColor : CARD_COLORS){
            if(aColor.equals(color))
                return true;
        }
        return false;
    }
    
    /**Returns the card's color*/
    public String getColor(){
        return color;
    }
    
    /**Returns the card's name*/
    public String getName() {
        return name;
    }
    
    /**Returns the card's score*/
    public Integer getScore(){
        return score;
    }
    
    /**Returns true if the card is a WildCard*/
    public boolean isWildCard(){
        return (getName().equals("DrawFour")||getName().equals("Mirror")||getName().equals("Wild"));
    }
    public boolean isActionCard(){
    	return (isWildCard() || getName().equals("Reverse") || getName().equals("DrawTwo") || getName().equals("Skip"));
    }
    
    /**Returns true if the card is a Numbered Card*/
    public boolean isNumbered(){
    	if(!this.isActionCard() && !this.isWildCard())
    		return true;
    	return false;
    }
    
    /**Checks if two cards match*/
    public abstract boolean match(Card card);
    
}
