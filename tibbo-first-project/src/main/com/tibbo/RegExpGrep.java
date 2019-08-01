package tibbo;

import java.util.regex.*;

import tibbo.grep.GrepException;

public class RegExpGrep extends MainGrep {
    Pattern pattern = null;
    public RegExpGrep(String value) throws GrepException {
        super(value);
        try {
            pattern = Pattern.compile(getTargetStr(), Pattern.CASE_INSENSITIVE);
        } catch (PatternSyntaxException ex) {
            System.out.println(ex);
        }

    }

    @Override
    //проверка входит ли подстрока в сторку
    public void checkValue(String value) {
    Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            getResultList().add(value);
        }
    }
}
