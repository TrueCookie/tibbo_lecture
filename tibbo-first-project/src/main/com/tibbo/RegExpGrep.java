package tibbo;
import java.util.regex.*;
import tibbo.grep.GrepException;

public class RegExpGrep extends MainGrep{
    public RegExpGrep(String value) throws GrepException {
        try {

        }
        super(value);
    }

    @Override
    //проверка входит ли подстрока в сторку
    public void checkValue(String value){
        Pattern pattern = null;
        try {
            pattern = Pattern.compile(getTargetStr(), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(value);
            if(matcher.find()){
                getResultList().add(value);
            }
        }catch (PatternSyntaxException ex){//should i check it in constructor
            System.out.println(ex);
        }

    }
}
