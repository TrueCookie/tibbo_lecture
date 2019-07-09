import java.nio.charset.Charset;

public class Main
{
  private static final String str = new String("This is the name of Main class");
  public static void main(String[] args)
  {
    System.out.println("Hello world!");
  }
  
  public Integer plus(int[] array)
  {
    Integer sum = 0;
    for(Integer cell : array ){//check Integer overflow
      sum += cell;
    }
    return sum;
  }
  
  public String encode(byte[] byteArray)
  {
    return new String(byteArray, 0, byteArray.length, Charset.forName("UTF-8"));
  }
  
  public Boolean stringContains(String value, String subString)
  {
    return value.contains(subString);
  }
  
  @Override
  public String toString()
  {
    return str;
  }
  
  public static Main getInstance()
  {
    return new Main();
  }
}
