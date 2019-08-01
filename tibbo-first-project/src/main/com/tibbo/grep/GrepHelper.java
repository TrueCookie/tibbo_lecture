package tibbo.grep;

import tibbo.StringGrep;
import tibbo.RegExpGrep;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrepHelper {
    //public static final String GREP_EXPCETION_MESSAGE = "Значение null не подходит для поиска";
    public static final String GREP_EXPCETION_MESSAGE = "String argument was null pointer";
    public static final String FIRST_SPLIT_SYMBOL = "\n";
    public static final String SECOND_SPLIT_SYMBOL = " ";

    public static Grep getInstance(Integer value, String regExpOrSubstring) throws GrepException {
        if (value == 0) {
            return new StringGrep(regExpOrSubstring);
        } else if (value == 1) {
            return new RegExpGrep(regExpOrSubstring);
        } else {
            throw new IllegalArgumentException("value can only be 1 or 0");
        }

        //0 == StringGrep(regExpOrSubstring);
        //1 == RegExpGrep(regExpOrSubstring);
        //в случае если value != 0 и не равно 1 нужно выбросить исклюечение IllegalArgumentException
        //в случе если regExpOrSubstring == null нужно выбросить исключение GrepException, но выбросить его нужно не отсюда, а из конструктора класса
    }

    public static List<String> prepareValues(String value, String splitSymbol) {
        return new ArrayList(Arrays.asList(value.split(splitSymbol)));
    }

    public static List<String> readValuesFromFile(String filePath){
        List<String> text = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
            while ((line = br.readLine()) != null) {
                text.add(line);
            }
        }catch(IOException ex){
            System.out.println(ex);
        }
        return text;
    }
}
