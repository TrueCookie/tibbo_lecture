package tibbo;
import tibbo.grep.Grep;

public class StringGrep extends MainGrep implements Grep {
    public StringGrep(String value){
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
