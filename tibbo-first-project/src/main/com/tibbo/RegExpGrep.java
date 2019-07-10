package tibbo;

public class RegExpGrep implements Grep {
    private List<String> resultList = null;
    private Integer valueSize = null;
    private Pattern pattern;

    //проверка входит ли подстрока в сторку
    public boolean contains(String regExpOrSubstring, String value){
        try(){
            pattern = Pattern.compile(regExpOrSubstring, Pattern.CASE_INSENSITIVE);
        }catch (PatternSyntaxException){
            System.out.println("Wrong finding regular expression value");
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
