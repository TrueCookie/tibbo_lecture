package tibbo.grep;

import tibbo.StringGrep;
import tibbo.RegExpGrep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GrepHelper {
    public static final String GREP_EXPCETION_MESSAGE = "Значение null не подходит для поиска";
  
  public static Grep getInstance(Integer value, String regExpOrSubstring) throws GrepException {
      if(value == 0){
          return new StringGrep(regExpOrSubstring);
      }else if(value == 1){
          return new RegExpGrep(regExpOrSubstring);
      }else {
          throw new IllegalArgumentException("value can only be 1 or 0");
      }

      //0 == StringGrep(regExpOrSubstring);
      //1 == RegExpGrep(regExpOrSubstring);
      //в случае если value != 0 и не равно 1 нужно выбросить исклюечение IllegalArgumentException
      //в случе если regExpOrSubstring == null нужно выбросить исключение GrepException, но выбросить его нужно не отсюда, а из конструктора класса
  }
  
  public static List<String> prepareValues(String value, String splitSymbol){
      //List<String> list= new ArrayList(Arrays.asList(value.split(splitSymbol)));
      return new ArrayList(Arrays.asList(value.split(splitSymbol)));
  }
  
  public static List<String> readValuesFromFile(String filePath) {
    return null;
  }
}
