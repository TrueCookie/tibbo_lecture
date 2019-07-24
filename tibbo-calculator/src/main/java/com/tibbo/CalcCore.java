package com.tibbo;

import net.sourceforge.jeval.*;
import junit.framework.TestCase;
import org.testng.annotations.Test;

public class CalcCore  extends TestCase {
    Evaluator evaluator = new Evaluator();

    @Test
    public void testJEval() throws EvaluationException {
        String result = toInt(evaluator.evaluate("round(5.1)"));
        assertEquals("5", result);
    }

    public String toInt(String str){
        return str.substring(0,str.length()-2);
    }

}
