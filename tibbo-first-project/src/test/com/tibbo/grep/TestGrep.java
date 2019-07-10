package tibbo.grep;

import junit.framework.TestCase;

import java.util.List;

public class TestGrep extends TestCase
{
  private Integer STRING_GREP = 0;
  private Integer REGEXP_GREP = 1;
  
  private static final String FIRST_SPLIT_SYMBOL = "\n";
  private static final String SECOND_SPLIT_SYMBOL = " ";
  private static final String FIRST_CONTAINS_SYMBOL = "ignite";
  private static final String SECOND_CONTAINS_SYMBOL = "igniteh2indexing";
  
  public void testStringGrep()
  {
    testAbstractGrep(STRING_GREP);
  }
  
  public void testRegExpGrep()
  {
    testAbstractGrep(REGEXP_GREP);
  }
  
  private void testAbstractGrep(int grepType)
  {
    List<String> values = GrepHelper.prepareValues(GrepTestHelper.STRING_VALUE, FIRST_SPLIT_SYMBOL);
    assertEquals(47, values.size());
  
    testGrepWithParameters(grepType, FIRST_CONTAINS_SYMBOL, 22, values);
  
    testGrepWithParameters(grepType, SECOND_CONTAINS_SYMBOL, 7, values);
  
    values = GrepHelper.prepareValues(GrepTestHelper.STRING_VALUE, SECOND_SPLIT_SYMBOL);
    assertEquals(800, values.size());
  
    testGrepWithParameters(STRING_GREP, FIRST_CONTAINS_SYMBOL, 22, values);
  
  }
  
  private void testGrepWithParameters(int grepType, String containsSymbol, int valuesSize, List<String> values)
  {
    Grep stringGrep = GrepHelper.getInstance(grepType, containsSymbol);
  
    assertNotNull(stringGrep);
    for (String value : values)
    {
      stringGrep.checkValue(value);
    }
  
    stringGrep.printAllValues();
  
    assertEquals(new Integer(valuesSize), stringGrep.getValuesSize());
  }
}
