/**
 * Created by lmikolas on 10/06/17.
 */
public abstract class Card {
    private String name;
    private Integer score;
    private String color;



    private static final String [] CARD_COLORS={"black", "red", "yellow", "green", "blue"};
    private static final String [] CARD_NAMES={"Zero", "One", "Two","Three","Four",
                                                "Five", "Six", "Seven","Eight", "Nine",
                                                    "DrawFour","Mirror", "Skip", "Reverse",
                                                        "DrawTwo", "Wild"};
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
        for(String aColor : CARD_NAMES){
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

    public abstract boolean match(Card card);

}
