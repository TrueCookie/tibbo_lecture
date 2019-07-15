package tibbo.sort;

import java.util.ArrayList;
import java.util.List;
//import java.lang.String;

public class CustomSortComparator implements Sort {
    List<String> resultList = new ArrayList<String>();
    boolean ascending;
    Character comparatorSymbol = null;

    @Override
    public int compare(Object o1, Object o2) {
        //Метод сравнения, возращает 1 0 или -1 d зависимости от того какое из значений больше
        //Что тут нужно будет сделать если мы сортируем в обратную стороную?
        int result;
        if (comparatorSymbol != null) {
            result = numOfOccurrances(o1.toString(), comparatorSymbol) - numOfOccurrances(o2.toString(), comparatorSymbol);
        } else {
            result = o1.toString().compareTo(o1.toString());
        }
        if (result < 0) {
            return -1;
        } else if (result > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void setComparatorSymbol(Character symbol) {
        //Если symbol == null то в таком случае у нас нет символа по которому мы будет сравнить строки.
        //Сравнить в таком случае нужно по общему количеству симоволов в строке
        comparatorSymbol = symbol;
    }

    @Override
    public void setAscending(Boolean value) {
        //Нужно запомнить в какую сторону мы сортирутем. true по возврастанию, false - по убыванию
        ascending = value;
    }

    @Override
    public void setValuesList(List<String> values) {
        //В этом случае нужно запомнить values.
        //Чтобы при сортировке не измениеть исходный список нужно как то его скопировать и запомнить уже копию;
        resultList = new ArrayList<>(values);
    }

    @Override
    public List<String> getvaluesList() {
        //вернуть список уже после сортировки
        return resultList;
    }

    @Override
    public void sort() {
        //Сам метод сортироки. Сортировем по количеству символов в строке.
        for(int left = 0; left < resultList.size(); left++){
            String value = resultList.get(left);
            int i = left -1;
            for(; i>=0; i--){
                if(compare(value, resultList.get(i)) == -1){
                    resultList.set(i+1, resultList.get(i));
                }else{
                    break;
                }
            }
            resultList.set(i+1, value);
        }
    }

    public int numOfOccurrances(String str, Character ch){
        int beginIndex = str.indexOf(ch);
        if(beginIndex + 1 == str.length()){
            return 1;
        }else if(beginIndex != -1){
            return 1 + numOfOccurrances(str.substring(beginIndex+1, str.length()-1), ch);
        }else if(beginIndex == -1){
            return 0;
        }
        return 0;
    }
}
