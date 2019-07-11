package tibbo;
import tibbo.grep.Grep;

import java.util.ArrayList;
import java.util.List;

public class StringGrep implements Grep {
    private List<String> resultList = new ArrayList();
    private String targetStr;
    public StringGrep(String value){
        targetStr = value;
    }

    @Override
    //проверка входит ли подстрока в сторку
    public void checkValue(String value){
        if(value.toLowerCase().contains(targetStr.toLowerCase())){
            resultList.add(value);
        }
    }

    @Override
    //вовращает список всех строк, которые подошли
    public List<String> getValuesList(){
        return resultList;
    }

    @Override
    //распечатать в консоль все значения
    public void printAllValues(){
        for(String str : resultList){
            System.out.println(str);
        }
    }

    @Override
    //вернуть размер списка
    public Integer getValuesSize(){
        return resultList.size();
    }
}
