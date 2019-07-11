package tibbo.grep;

import tibbo.StringGrep;
import tibbo.RegExpGrep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Throwable;

public class GrepHelper
{
  public static Grep getInstance(Integer value, String regExpOrSubstring){
      if(value == 0){
          return new StringGrep(regExpOrSubstring);
      }else if(value == 1){
          return new RegExpGrep(regExpOrSubstring);
      }else {
            try{
                throw new Throwable("value can only be 1 or 0");
            }catch (Throwable ex){
                System.out.println(ex);
            }
            return null;
      }
  }
  
  public static List<String> prepareValues(String value, String splitSymbol){
      List<String> list= new ArrayList(Arrays.asList(value.split(splitSymbol)));
      return list;
  }
}
