package tibbo;
import java.util.regex.*;
import java.util.ArrayList;
import tibbo.grep.Grep;
import java.util.List;

public class RegExpGrep implements Grep {
    List resultList = new ArrayList();
    private String targetStr;
    public RegExpGrep(String value){
        targetStr = value;
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

    @Override
    //вовращает список всех строк, которые подошли
    public List getValuesList(){
        return resultList;
    }

    @Override
    //распечатать в консоль все значения
    public void printAllValues(){
        for(Object str : resultList){
            System.out.println(str);
        }
    }

    @Override
    //вернуть размер списка
    public Integer getValuesSize(){
        return resultList.size();
    }
}
