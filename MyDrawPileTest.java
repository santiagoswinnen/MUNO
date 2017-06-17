/**
 * @author jlococo
 */
public class MyDrawPileTest {

    /**
     * Verifica que al agregar una carta disminuya el size
     */
    @Test
    public void testGetCardSize(){
        UNOGame game;
        DrawPile drawPile = new DrawPile(game);
        int prueba = drawPile.getDrawPile().size();
        drawPile.getCard();
        AssertTrue(prueba!=drawPile.getDrawPile().size());
    }

    public void static Test suite(){
        return new TestSuite(MyDealerTest.class);
    }
    public static void main(String[] args){
        junit.textui.TestRunner.run(suite());
    }
}
