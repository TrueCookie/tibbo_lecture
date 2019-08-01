package tibbo;
import tibbo.grep.Grep;
import tibbo.grep.GrepException;

public class StringGrep extends MainGrep{
    public StringGrep(String value) throws GrepException {
        super(value);
    }

    @Override
    //проверка входит ли подстрока в сторку
    public void checkValue(String value){
        if(value.toLowerCase().contains(getTargetStr().toLowerCase())){
            getResultList().add(value);
        }
    }
}
