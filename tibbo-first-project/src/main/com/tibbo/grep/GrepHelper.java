package tibbo.grep;

import tibbo.StringGrep;

public class GrepHelper
{
  public static Grep getInstance(Integer value)
  {
    //value == 0 создаем StringGrep
    //value == 1 создаем RegExpGrep
    Grep grep = null;
    if(value == 0){
      grep = new StringGrep();
    }else if(value == 1){
      grep = new RegExpGrep()
    }
    return grep;
  }
}
