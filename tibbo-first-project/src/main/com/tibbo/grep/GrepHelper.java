package tibbo.grep;

//import tibbo.StringGrep;
import java.util.Arrays;
import java.util.List;

public class GrepHelper
{
  public static Grep getInstance(Integer value, String regExpOrSubstring){
      //0 == StringGrep(regExpOrSubstring);
      //1 == RegExpGrep(regExpOrSubstring);
    Grep grep = null;
    if(value == 0){
      grep = new StringGrep(regExpOrSubstring);
    }else if(value == 1){
      grep = new RegExpGrep(regExpOrSubstring);
    }
    return grep;
    return null;
  }
  
  //public static List<String> prepareValues(String value, String splitSymbol){}
}
