package tibbo;
import java.util.regex.*;
import tibbo.grep.Grep;
import tibbo.grep.GrepException;

public class RegExpGrep extends MainGrep implements Grep {
    public RegExpGrep(String value)throws GrepException {
        super(value);
    }

    @Override
    //проверка входит ли подстрока в сторку
    public void checkValue(String value) {
        Pattern pattern = null;
        try {
            pattern = Pattern.compile(getTargetStr(), Pattern.CASE_INSENSITIVE);
        }catch (PatternSyntaxException ex){
            System.out.println(ex);
        }
        Matcher matcher = pattern.matcher(value);
        if(matcher.find()){
            getResultList().add(value);
        }
    }
}
