package tibbo.grep;

import junit.framework.TestCase;

public class TestGrep extends TestCase
{
  public void testGrep()
  {
    Grep stringGrep = GrepHelper.getInstance(0);
    stringGrep.contains("tibbo", "this is first tibbo string");
    
    Grep regexpGrep = GrepHelper.getInstance(1);
  
    regexpGrep.contains("tibbo", "this is first tibbo string");
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
