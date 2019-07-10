package tibbo.grep;

import java.util.List;

public interface Grep
{
  /*
  * Реализовать интерфейс Grep;
  * 1) Создать класс StringGrep
  * 2) Создать RegExpGrep
  *
  * */
  
  //проверка входит ли подстрока в сторку, либо regexp что то нашел;
  public void contains(String regExpOrSubstring, String value);
  
  //вовращает список всех строк, которые подошли
  public List<String> getValuesList();
  
  //распечатать в консоль все значения
  public void printAllValues();
  
  //вернуть размер списка
  public Integer getValuesSize();
}
