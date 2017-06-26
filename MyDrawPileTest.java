/**
 * @author jlococo
 */
public class MyDrawPileTest {

    /**
     * Verifica que al agregar una carta disminuya el size
     */
    @Test
    public void static Test suite(){
        return new TestSuite(MyDealerTest.class);
    }
    public static void main(String[] args){
        junit.textui.TestRunner.run(suite());
    }
}
