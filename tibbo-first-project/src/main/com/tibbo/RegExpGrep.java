package tibbo;
import java.util.regex.*;
import tibbo.grep.GrepException;

public class RegExpGrep extends MainGrep{
    public RegExpGrep(String value)throws GrepException {
        super(value);
    }

    @Override
    //проверка входит ли подстрока в сторку
    public void checkValue(String value) throws NullPointerException{
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
