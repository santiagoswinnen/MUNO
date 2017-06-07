/**
 * Created by lmikolas on 07/06/17.
 */
public class ActionCard extends Card{
    Behaviour b;

    ActionCard(String color, Behaviour action){
        super(color);
        b = action;
    }

    /*Esta accion se va ejecutar cuando sea el turno del siguiente */
    public void makeAction() {
        b.action();
    }



}
