package tibbo;
import java.util.regex.*;
import java.util.ArrayList;

public class RegExpGrep implements Grep {
    List resultList = new ArrayList();
    //private Integer valueSize = null;

    //проверка входит ли подстрока в сторку
    public boolean contains(String regExpOrSubstring, String value){
        try{
            Pattern pattern = Pattern.compile(regExpOrSubstring, Pattern.CASE_INSENSITIVE);
        }catch (PatternSyntaxException ex){
            System.out.println(ex);
        }
        Matcher matcher = pattern.matcher(value);
        if(matcher.find()){
            resultList.add(value);
            return true;
        }else{
            return false;
        }
    }

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
    public Integer getValueSize(){
        return resultList.size();
    }
}
