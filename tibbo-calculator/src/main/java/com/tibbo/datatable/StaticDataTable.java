package com.tibbo.datatable;

import com.tibbo.aggregate.common.datatable.*;

import java.util.Date;

public class StaticDataTable {
    private static DataTable simpleTable;
    private static DataTable innerTable;
    private static DataTable bigTable;

    private static TableFormat tableFormat;
    static{
        tableFormat = new TableFormat(1, 1);

        setSimpleTable();
        setInnerTable();
        setBigTable();
    }

    public static DataTable getSimpleTable() {
        return simpleTable;
    }

    public static DataTable getInnerTable() {
        return innerTable;
    }

    public static DataTable getBigTable() {
        return bigTable;
    }

    public static void setSimpleTable(){
        //TableFormat tableFormat = new TableFormat(1, 1);
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
        tableFormat.addField(FieldFormat.DATATABLE_FIELD, StaticDataTableHelper.FIELD_DATATABLE_FIELD, "Field Description", innerTable, true);
        simpleTable = new SimpleDataTable(tableFormat);
    }

    public static void setInnerTable(){
        //TableFormat tableFormat = new TableFormat();
        tableFormat.setMinRecords(1);
        tableFormat.setMaxRecords(1);

        innerTable = new SimpleDataTable(tableFormat);




        DataRecord firstRec = innerTable.addRecord();
        firstRec.setValue(7, innerTable);

        innerTable.addRecord();
        innerTable.addRecord();
        innerTable.addRecord();
        innerTable.addRecord();
    }

    public static void setBigTable(){
        /*TableFormat tableFormat = new TableFormat();
        tableFormat.addField(FieldFormat.DATATABLE_FIELD, StaticDataTableHelper.FIELD_DATATABLE_FIELD, "It's datatable field", innerTable, true);
        innerTable = new SimpleDataTable(tableFormat);

        DataRecord rec = innerTable.addRecord();
        rec.setValue(StaticDataTableHelper.FIELD_INTEGER_TEST, 200);
        rec.setValue(1, 1L);
        rec.setValue(2, 321.1);

        innerTable.addRecord();
        innerTable.addRecord();
        innerTable.addRecord();
        innerTable.addRecord();
        innerTable.addRecord(innerTable);*/
    }
}
