package tibbo.grep;

import java.util.List;

public interface Grep {
  /*
  * Реализовать интерфейс Grep;
  * 1) Создать класс StringGrep
  * 2) Создать RegExpGrep
  * */
  
  //значение которое нужно искать в строку я передаю в качетсве параметра класса в конструкторе;

  //проверка входит ли подстрока в сторку, либо regexp что то нашел;
  void checkValue(String value);
  
  //вовращает список всех строк, которые подошли
  List<String> getValuesList();
  
  //распечатать в консоль все значения
  void printAllValues();
  
  //вернуть размер списка
  Integer getValuesSize();
}
