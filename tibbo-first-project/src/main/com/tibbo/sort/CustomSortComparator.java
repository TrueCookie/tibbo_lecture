package tibbo.sort;

import java.util.List;

public class CustomSortComparator implements Sort
{
  @Override
  public int compare(Object o1, Object o2)
  {
    //Метод сравнения, возращает 1 0 или -1 d зависимости от того какое из значений больше
    //Что тут нужно будет сделать если мы сортируем в обратную стороную?
    return 0;
  }
  
  @Override
  public void setComparatorSymbol(Character symbol)
  {
    //Если symbol == null то в таком случае у нас нет символа по которому мы будет сравнить строки.
    //Сравнить в таком случае нужно по общему количеству симоволов в строке
  }
  
  @Override
  public void setAscending(Boolean value)
  {
    //Нужно запомнить в какую сторону мы сортирутем. true по возврастанию, false - по убыванию
  }
  
  @Override
  public void setValuesList(List<String> values)
  {
    //В этом случае нужно запомнить values.
    //Чтобы при сортировке не измениеть исходный список нужно как то его скопировать и запомнить уже копию;
  }
  
  @Override
  public List<String> getvaluesList()
  {
    //вернуть список уже после сортировки
    return null;
  }
  
  @Override
  public void sort()
  {
    //Сам метод сортироки. Сортировем по количеству символов в строке.
  }
}
