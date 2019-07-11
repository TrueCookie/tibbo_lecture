package tibbo;
import java.util.regex.*;
import tibbo.grep.Grep;

public class RegExpGrep extends MainGrep implements Grep {
    public RegExpGrep(String value){
        super(value);
    }

    @Override
    //проверка входит ли подстрока в сторку
    public void checkValue(String value) {
        Pattern pattern = null;
        try {
            pattern = Pattern.compile(targetStr, Pattern.CASE_INSENSITIVE);
        }catch (PatternSyntaxException ex){
            System.out.println(ex);
        }
        Matcher matcher = pattern.matcher(value);
        if(matcher.find()){
            resultList.add(value);
        }
    }
}
