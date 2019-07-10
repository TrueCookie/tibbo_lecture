package tibbo;

import java.nio.charset.StandardCharsets;

public class MainTestHelper
{
  public static final int[] INT_ARRAY = new int[14];
  
  static 
  {
    INT_ARRAY[0] = 101236;
    INT_ARRAY[1] = 1012536;
    INT_ARRAY[2] = 1435636;
    INT_ARRAY[3] = 102136;
    INT_ARRAY[4]= 153436;
    INT_ARRAY[5]= 145636;
    INT_ARRAY[6] = 1765436;
    INT_ARRAY[7] = 1765436;
    INT_ARRAY[8] = 145736;
    INT_ARRAY[9]= 10645336;
    INT_ARRAY[10] = 106435636;
    INT_ARRAY[11] = 1065436;
    INT_ARRAY[12]= 10654336;
    INT_ARRAY[13] = 10534236;
  }
  
  public static String STRING_STRING = "new super pupper dupper string";
  
  public static byte[] STRING_ARRAY = "new super pupper dupper string".getBytes(StandardCharsets.UTF_8);

  public static String MAIN_NAME = "This is the name of tibbo.string.tibbo.Main class";
}
