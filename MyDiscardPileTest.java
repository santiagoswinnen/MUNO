package muno.game;
/**
 * @author jlococo
 */
public class MyDiscardPileTest {
    /**
     * Analiza la ultima carta, si hay una sola
     */
    @Test
    public void testLastCardWithOneCard() {
        DiscardPile discardPile = new DiscardPile();
        Card card= new NumberedCard("Zero", 0, "red");
        assertSame(card, discardPile.lastCard());
    }
    
    /**
    *Analiza la ultima carta, si no hay.
    */
    @Test
    public void testLastCardWithoutCards(){
        DiscardPile discardPile = new DiscardPile();
        try{
            discardPile.lastCard();
            fail("Deberia haber tirado error.");
        }catch (IndexOutOfBoundsException e){

        }
      }

    /**
     * Se verifica que este tomando bien las cartas
     */
    
    @Test
    public void testAskCardsWithACard(){
        DiscardPile discardPile = new DiscardPile();
        Card card= new NumberedCard("Zero", 0, "red");
        discardPile.throwCard(card1);
        assertNull(askCards());
        assertSame(card, discardPile.lastCard());
    }

    /**
     * Se verifica que con dos cartas toma 
     * la que se encuentra abajo y deja la
     * que esta boca arriba.
     */
    @Test    
    public void testAskCardsWithTwoCards(){
        DiscardPile discardPile = new DiscardPile();
        Card card= new NumberedCard("Zero", 0, "red");
        Card card2= new NumberedCard("Zero", 0, "blue");
        discardPile.throwCard(card);
        discardPile.throwCard(card2);
        assertNotNull(askCards());
        assertSame(card2, discardPile.lastCard());
    }

    /**
     * @after deneria tener size=1; 
     */
    @Test
    public void testThrowCard(){
        DiscardPile discardPile = new DiscardPile();
        assertEquals(getDiscardPile().size(),0);
        Card card= new NumberedCard("Zero", 0, "red");
        assertEquals(getDiscardPile().size(),1);
    }
    
    public void static Test suite(){
        return new TestSuite(MyDealerTest.class);
    }
    public static void main(String[] args){
        junit.textui.TestRunner.run(suite());
    }
}
