package muno.game;

/**
mmm
 * Created by lmikolas on 10/06/17.
 */
public abstract class Card {
    private String name;
    private Integer score;
    private String color;



    private static final String [] CARD_COLORS={"black", "red", "yellow", "green", "blue"};
    private static final String [] CARD_NAMES={"Zero", "One", "Two","Three","Four",
                                                "Five", "Six", "Seven","Eight", "Nine",
                                                    "DrawTwo","Reverse", "Skip", "Mirror","DrawFour"
                                                        , "Wild"};
    private static final Integer [] CARD_SCORES={0,1,2,3,4,5,6,7,8,9,20,50};



    


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
    
    public void setColor(String color){
        if(!isWildCard()) {
            throw new UnsupportedOperationException("can't change color to this card");
        }
        if(!checkColor(color)){
            throw new IllegalArgumentException("not a valid color");
        }
        this.color = color;
    }
    
    public static String[] getCardNames(){
        return CARD_NAMES;
    }
    
    public static String[] getCardColors(){
        return CARD_COLORS;
    }
    
    public boolean checkName(String name){
        for(String cardName : CARD_NAMES){
            if(cardName.equals(name))
                return true;
        }
        return false;
    }

    public boolean checkScore(Integer score){
        for(Integer aScore : CARD_SCORES){
            if(score.equals(aScore))
                return true;
        }
        return false;
    }

    public static boolean checkColor(String color){
        for(String aColor : CARD_COLORS){
            if(aColor.equals(color))
                return true;
        }
        return false;

    }


    public String getColor(){
        return color;
    }

    public String getName() {
        return name;
    }

    public Integer getScore(){
        return score;
    }
    
    public boolean isWildCard(){
        return (getName().equals("DrawFour")||getName().equals("Mirror")||getName().equals("Wild"));
    }
    
    public abstract boolean match(Card card);

}
