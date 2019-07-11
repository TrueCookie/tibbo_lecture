package tibbo;

import java.util.ArrayList;
import java.util.List;

public class MainGrep {
    private List<String> resultList = new ArrayList();
    private String targetStr;

    public MainGrep(String value){
        if(value == null){
            try{
                throw new NullPointerException("String argument was null pointer");
            }catch (NullPointerException ex){
                System.out.println(ex);
            }
        }
        targetStr = value;
    }

    public List<String> getResultList(){
        return resultList;
    }

    public String getTargetStr() {
        return targetStr;
    }

    //вовращает список всех строк, которые подошли
    public List getValuesList(){
        return resultList;
    }

    //распечатать в консоль все значения
    public void printAllValues(){
        for(String str : resultList){
            System.out.println(str);
        }
    }

    //вернуть размер списка
    public Integer getValuesSize(){
        return resultList.size();
    }
}
