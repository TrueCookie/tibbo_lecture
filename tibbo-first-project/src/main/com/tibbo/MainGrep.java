package tibbo;

import tibbo.grep.Grep;
import tibbo.grep.GrepException;

import java.util.ArrayList;
import java.util.List;

public abstract class MainGrep implements Grep {
    private List<String> resultList = new ArrayList();
    private String targetStr;

    public MainGrep(String value) throws GrepException{
        if (value == null) {
            throw new GrepException("String argument was null pointer");
        }
        targetStr = value;
    }

    public List<String> getResultList() {
        return resultList;
    }

    public String getTargetStr() {
        return targetStr;
    }

    //вовращает список всех строк, которые подошли
    public List getValuesList() {
        return resultList;
    }

    //распечатать в консоль все значения
    public void printAllValues() {
        for (String str : resultList) {
            System.out.println(str);
        }
    }

    //вернуть размер списка
    public Integer getValuesSize() {
        return resultList.size();
    }
}
