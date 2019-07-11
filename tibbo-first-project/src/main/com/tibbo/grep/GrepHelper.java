package tibbo.grep;

import tibbo.StringGrep;
import tibbo.RegExpGrep;

import java.util.ArrayList;
import java.util.List;

public class GrepHelper
{
  public static Grep getInstance(Integer value, String regExpOrSubstring){
    Grep grep = null;
    if(value == 0){
      grep = new StringGrep(regExpOrSubstring);
    }else if(value == 1){
      grep = new RegExpGrep(regExpOrSubstring);
    }
    return grep;
  }
  
  public static List<String> prepareValues(String value, String splitSymbol){
      List<String> list= new ArrayList();
      String[] arr = value.split(splitSymbol);
      for(String str : arr){
          list.add(str);
      }
      return list;
  }
}
