package tibbo.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomSortComparator implements Sort {
    private List<String> resultList = new ArrayList<>();
    private boolean ascending;
    private Character comparatorSymbol = null;

    @Override
    public int compare(Object o1, Object o2) {
        //Метод сравнения, возращает 1 0 или -1 d зависимости от того какое из значений больше
        //Что тут нужно будет сделать если мы сортируем в обратную стороную?
        int result;
        if(o1 == null && o2 == null){
            return 0;
        }else if(o2 == null){
            return 1;
        }else if(o1 == null){
            return -1;
        } else if(!(o1 instanceof String)){
            return 1;
        }else if(!(o2 instanceof String)){
            return -1;
        }
        String o1Str = (String)o1;
        String o2Str = (String)o2;
        if (comparatorSymbol != null) {
            result = numOfOccurrances(o1Str) - numOfOccurrances(o2Str);
        } else {
            result = o1.toString().length() - o2.toString().length();
        }
        return ascending ? result : -result;
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

    //@Override
    public void sort() {
        //Сам метод сортироки. Сортировем по количеству символов в строке.
        quickSort(0, resultList.size()-1);
    }

    private void quickSort(int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (compare(resultList.get(i),resultList.get(cur)) < 1)) {
                i++;
            }
            while (j > cur && (compare(resultList.get(cur),resultList.get(j)) < 1)) {
                j--;
            }
            if (i < j) {
                Collections.swap(resultList, i, j);
                if (i == cur){
                    cur = j;
                }else if (j == cur){
                    cur = i;
                }
            }
        }
        quickSort(start, cur);
        quickSort(cur+1, end);
    }

    private int numOfOccurrances(String str){
        int count = 0;
        for(Character symbol : str.toString().toCharArray()){
            if(symbol == comparatorSymbol){
                count++;
            }
        }
        return count;
    }
}
