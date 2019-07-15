package tibbo.sort;

import junit.framework.TestCase;
import tibbo.grep.GrepHelper;
import tibbo.grep.GrepTestHelper;

import java.util.List;

public class TestSort extends TestCase
{
  public void testSort() throws Exception
  {
    List<String> values = GrepHelper.prepareValues(GrepTestHelper.STRING_VALUE, GrepHelper.FIRST_SPLIT_SYMBOL);
    Sort sorter = new CustomSortComparator();
    assertNotNull(sorter);
    sorter.setValuesList(values);
    assertEquals(47, sorter.getvaluesList().size());
    sorter.setAscending(true);
    sorter.setComparatorSymbol(null);
    sorter.sort();
    assertEquals(47, sorter.getvaluesList().size());
    
    assertNotSame(values, sorter.getvaluesList());
  
    sorter = new CustomSortComparator();
    assertNotNull(sorter);
    sorter.setValuesList(values);
    assertEquals(47, sorter.getvaluesList().size());
    sorter.setAscending(false);
    sorter.setComparatorSymbol('a');
    sorter.sort();
    assertEquals(47, sorter.getvaluesList().size());
  
    assertNotSame(values, sorter.getvaluesList());
  
    sorter = new CustomSortComparator();
    assertNotNull(sorter);
    sorter.setValuesList(values);
    assertEquals(47, sorter.getvaluesList().size());
    sorter.setAscending(false);
    sorter.setComparatorSymbol('.');
    sorter.sort();
    assertEquals(47, sorter.getvaluesList().size());
    
    assertNotSame(values, sorter.getvaluesList());
    
  }
}
