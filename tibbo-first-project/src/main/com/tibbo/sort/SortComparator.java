package tibbo.sort;

import java.util.Comparator;

public interface SortComparator extends Comparator
{
  public void setComparatorSymbol(Character symbol);
  
  public void setAscending(Boolean value);
}
