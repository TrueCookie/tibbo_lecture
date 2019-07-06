import junit.framework.TestCase;

public class MainTest extends TestCase
{
  public void testMainClass() throws Exception
  {
    Main main = new Main();
    assertNotNull(main);
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
