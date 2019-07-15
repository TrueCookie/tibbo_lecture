package tibbo.grep;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class TestGrep extends TestCase {

    private static final String FIRST_SPLIT_SYMBOL = "\n";
    private static final String SECOND_SPLIT_SYMBOL = " ";
    private static final String FIRST_CONTAINS_SYMBOL = "ignite";
    private static final String SECOND_CONTAINS_SYMBOL = "igniteh2indexing";
    private static final int REGEXP_GREP = 1;
    private static final int STRING_GREP = 0;

    @Test
    public void testStringGrep() throws Exception {
        testAbstractGrep(STRING_GREP);
    }


    @Test
    public void testRegExpGrep() throws Exception {
        testAbstractGrep(REGEXP_GREP);
    }


    @Test
    public void testReadFromFileAndExceptions() throws Exception {
        List<String> values = GrepHelper.readValuesFromFile("data" + File.separator + "data.txt");
        assertNotNull(values);

        System.out.println(values.size());
        assertEquals(40291, values.size());

        Grep stringGrep = GrepHelper.getInstance(STRING_GREP, FIRST_CONTAINS_SYMBOL);
        Grep regExpGrep = GrepHelper.getInstance(REGEXP_GREP, SECOND_CONTAINS_SYMBOL);

        assertNotNull(stringGrep);
        assertNotNull(regExpGrep);

        boolean exceptionHapped = false;
        try {
            GrepHelper.getInstance(10, null);
        } catch (Exception ex) {
            assertEquals(ex.getClass(), IllegalArgumentException.class);
            exceptionHapped = true;
        }

        assertTrue(exceptionHapped);

        exceptionHapped = false;
        try {
            GrepHelper.getInstance(STRING_GREP, null);
        } catch (GrepException ex) {
            assertEquals(ex.getClass(), GrepException.class);
            assertEquals(ex.getMessage(), GrepHelper.GREP_EXPCETION_MESSAGE);
            exceptionHapped = true;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Найдено не подходящее исключение");
        }
        assertTrue(exceptionHapped);
    }

    /*private void testAbstractGrep(int grepType) throws Exception {
        List<String> values = GrepHelper.prepareValues(GrepTestHelper.STRING_VALUE, FIRST_SPLIT_SYMBOL);
        assertNotNull(values);
        assertEquals(47, values.size());

        testGrepWithParameters(grepType, FIRST_CONTAINS_SYMBOL, 22, values);

        testGrepWithParameters(grepType, SECOND_CONTAINS_SYMBOL, 7, values);

        values = GrepHelper.prepareValues(GrepTestHelper.STRING_VALUE, SECOND_SPLIT_SYMBOL);
        assertNotNull(values);
        assertEquals(800, values.size());

        testGrepWithParameters(grepType, FIRST_CONTAINS_SYMBOL, 22, values);

    }*/

    /*private void testGrepWithParameters(int grepType, String containsSymbol, int valuesSize, List<String> values) throws Exception {
        Grep stringGrep = GrepHelper.getInstance(grepType, containsSymbol);

        assertNotNull(stringGrep);
        for (String value : values) {
            stringGrep.checkValue(value);
        }

        stringGrep.printAllValues();

        assertEquals(new Integer(valuesSize), stringGrep.getValuesSize());
        assertTrue(exceptionHapped);
    }*/

    private void testAbstractGrep(int grepType) throws Exception {
        List<String> values = GrepHelper.prepareValues(GrepTestHelper.STRING_VALUE, GrepHelper.FIRST_SPLIT_SYMBOL);
        assertNotNull(values);
        assertEquals(47, values.size());

        testGrepWithParameters(grepType, FIRST_CONTAINS_SYMBOL, 22, values);

        testGrepWithParameters(grepType, SECOND_CONTAINS_SYMBOL, 7, values);

        values = GrepHelper.prepareValues(GrepTestHelper.STRING_VALUE, GrepHelper.SECOND_SPLIT_SYMBOL);
        assertNotNull(values);
        assertEquals(800, values.size());

        testGrepWithParameters(grepType, FIRST_CONTAINS_SYMBOL, 22, values);
    }

    private void testGrepWithParameters(int grepType, String containsSymbol, int valuesSize, List<String> values) throws Exception {
        Grep stringGrep = GrepHelper.getInstance(grepType, containsSymbol);

        assertNotNull(stringGrep);
        for (String value : values) {
            stringGrep.checkValue(value);
        }
    }
}
