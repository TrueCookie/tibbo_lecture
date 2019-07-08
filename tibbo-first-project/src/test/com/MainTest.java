import junit.framework.TestCase;
import org.junit.Test;

public class MainTest extends TestCase
{
  @Test
  public void testMainClass() throws Exception
  {
    Main main = new Main();
    assertNotNull(main);
  }
  
  @Test
  public void testPlus() throws Exception
  {
    Main main = new Main();
    int result = main.plus(MainTestHelper.INT_ARRAY);
    assertEquals(145962204, result);
  }
  
  @Test
  public void testEncoding()
  {
    Main main = new Main();
    assertEquals(MainTestHelper.STRING_STRING, main.encode(MainTestHelper.STRING_ARRAY));
  }
  
  public void testMainName()
  {
    Main main = new Main();
    assertEquals(MainTestHelper.MAIN_NAME, main.toString());
  }
  
  @Test
  public void testContains()
  {
    Main main = new Main();
    assertFalse(main.stringContains(main.encode(MainTestHelper.STRING_ARRAY), "abraCadabra"));
    assertTrue(main.stringContains(main.encode(MainTestHelper.STRING_ARRAY), "super"));
  }
  
  @Override
  protected void setUp() throws Exception
  {
    super.setUp();
  }
  
  @Override
  protected void tearDown() throws Exception
  {
    super.tearDown();
  }
}
