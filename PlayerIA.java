/**
 * Created by lmikolas on 08/06/17.
 */
public class PlayerIA extends Player {

    PlayerIA(String name, Game game){
        super(name, game);
    }


    /*tira la primera carta que matchea, si puedo o si no simplemente levanta*/
    public void makeMove(){
        boolean foundCard = false;
        for(Card c: hand) {
            if (!foundCard && c.match(game.getDealer().lastCard())) {
                throwCard(c);
                foundCard = true;
            }
        }
        if(!foundCard)
            addCard(game.getDealer().drawCard());
    }
}
