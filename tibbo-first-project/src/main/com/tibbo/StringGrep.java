package tibbo;
import java.util.List;

public class StringGrep implements Grep{
    private List<String> resultList = null;
    private Integer valueSize = null;

    //проверка входит ли подстрока в сторку
    public boolean contains(String regExpOrSubstring, String value){
        if(value.toLowerCase().contains(regExpOrSubstring.toLowerCase())){
            resultList.add(value);
            return true;
        }
        return false;
    }

    //вовращает список всех строк, которые подошли
    public List<String> getValuesList(){
        return resultList;
    }

    //распечатать в консоль все значения
    public void printAllValues(){
        for(String str : resultList){
            System.out.println(str);
        }
    }

    //вернуть размер списка
    public Integer getValueSize(){
        return resultList.size();
    }
}
