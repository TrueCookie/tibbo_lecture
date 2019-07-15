package tibbo;

import java.util.regex.*;

import tibbo.grep.GrepException;

public class RegExpGrep extends MainGrep {
    Pattern pattern = null;
    Matcher matcher = null;
    public RegExpGrep(String value) throws GrepException {
        super(value);
        try {
            pattern = Pattern.compile(getTargetStr(), Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(value);
        } catch (PatternSyntaxException ex) {
            System.out.println(ex);
        }

    }

    @Override
    //проверка входит ли подстрока в сторку
    public void checkValue(String value) {
        if (matcher.find()) {
            getResultList().add(value);
        }
    }
}
