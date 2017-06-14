/**
 * Created by lmikolas on 07/06/17.
 */
public class ActionCard extends Card{
    Behaviour[] behaviours;
    private int behaviourCounter=0;

    ActionCard(String name, Integer score, String color, Behaviour...behaviours){
        super(name, score, color);
        this.behaviours = behaviours;
    }


    public void makeAction(String ... args) {
            int k = 0;
            if(args.length == k){
                behaviours[k].action(args);
            }
            else if(args.length == 1){
                if(behaviours.length != 2)
                    throw new UnsupportedOperationException("Not enough arguemnts for behaviour")
                behaviours[1].action(args);
            }
    }

    public void setColor(String color){
        if(behaviours[behaviourCounter].setColor())
    }


    public boolean match(Card card){
        if(this.getColor().equals("black") ||  this.getColor().equals(card.getColor())){
            return true;
        }
        return false;

    }



}
