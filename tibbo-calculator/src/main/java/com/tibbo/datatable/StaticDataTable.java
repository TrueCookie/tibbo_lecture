package com.tibbo.datatable;

import com.tibbo.aggregate.common.datatable.*;

import java.util.Date;

public class StaticDataTable {
    private static DataTable simpleTable;
    private static DataTable secondTable;
    private static DataTable bigTable;

    static{
        setSimpleTable();
        setSecondTable();
        setBigTable();
    }

    public static DataTable getSimpleTable() {
        return simpleTable;
    }

    public static DataTable getInnerTable() {
        return secondTable;
    }

    public static DataTable getBigTable() {
        return bigTable;
    }

    public static void setSimpleTable(){
        TableFormat tableFormat = new TableFormat(1, 1);
        FieldFormat stringField = FieldFormat.create(StaticDataTableHelper.FIELD_STRING_TEST, FieldFormat.STRING_FIELD, "It's a simple string", "Default Value", true);
        stringField.setHidden(true);
        stringField.setReadonly(true);

        tableFormat.addField(FieldFormat.INTEGER_FIELD, StaticDataTableHelper.FIELD_INTEGER_TEST, "Field Description", "1", true);
        tableFormat.addField(FieldFormat.LONG_FIELD, StaticDataTableHelper.FIELD_LONG_FIELD, "Field Description", "1", true);
        tableFormat.addField(FieldFormat.FLOAT_FIELD, StaticDataTableHelper.FIELD_FLOAT_FIELD, "Field Description", "1.0", true);
        tableFormat.addField(FieldFormat.DATE_FIELD, StaticDataTableHelper.FIELD_DATE_FIELD, "Field Description", new Date(1_000_000L), true);
        tableFormat.addField(FieldFormat.BOOLEAN_FIELD, StaticDataTableHelper.FIELD_BOOLEAN_FIELD, "Field Description", "true", true);
        tableFormat.addField(stringField);
        tableFormat.addField(FieldFormat.DOUBLE_FIELD, StaticDataTableHelper.FIELD_DOUBLE_FIELD, "Field Description", "1.0", true);
        tableFormat.addField(FieldFormat.DATATABLE_FIELD, StaticDataTableHelper.FIELD_DATATABLE_FIELD, "Field Description", null, true);
        simpleTable = new SimpleDataTable(tableFormat);
    }

    public static void setSecondTable(){
        TableFormat tableFormat = new TableFormat();
        FieldFormat stringField = FieldFormat.create(StaticDataTableHelper.FIELD_STRING_TEST, FieldFormat.STRING_FIELD, "It's a simple string", "Default Value", true);
        stringField.setHidden(true);
        stringField.setReadonly(true);

        tableFormat.addField(FieldFormat.DATATABLE_FIELD, StaticDataTableHelper.FIELD_DATATABLE_FIELD, "Field Description", null, true);
        secondTable = new SimpleDataTable(tableFormat);

        secondTable.addRecord(getSimpleTable());
        secondTable.addRecord();
        secondTable.addRecord();
        secondTable.addRecord();
        secondTable.addRecord();
    }

    public static void setBigTable(){
        TableFormat tableFormat = new TableFormat();
        tableFormat.addField(FieldFormat.INTEGER_FIELD, StaticDataTableHelper.FIELD_INTEGER_TEST, "It's just a field", null, true);
        tableFormat.addField(FieldFormat.STRING_FIELD, StaticDataTableHelper.FIELD_STRING_TEST, "It's just a field", null, true);
        bigTable = new SimpleDataTable(tableFormat);

        bigTable.addRecord(10, "10");
        for(int i = 0; i < 44; ++i){
            bigTable.addRecord();
        }
        bigTable.addRecord(50, "50");
        for(int i = 0; i < 4; ++i){
            bigTable.addRecord();
        }
    }

}
