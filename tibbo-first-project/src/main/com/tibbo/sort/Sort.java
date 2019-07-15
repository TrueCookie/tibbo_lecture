package tibbo.sort;

import java.util.List;

public interface Sort extends SortComparator
{
  public void setValuesList(List<String> values);
  
  public List<String> getvaluesList();
  
  public void sort();
}
