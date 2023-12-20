package vo;

import java.util.ArrayList;

public class Table {
    private String tableName;
    private ArrayList<String> primaryKeys = new ArrayList<>();
    private ArrayList<String> foreignKeys = new ArrayList<>();
    private ArrayList<String> indexList = new ArrayList<>();
    private ArrayList<String> uniqueColumns = new ArrayList<>();
    private ArrayList<String> columns = new ArrayList<>();
    private ArrayList<String> columnTypes = new ArrayList<>();
    private ArrayList<String> nulls = new ArrayList<>();
    private ArrayList<String> defaults = new ArrayList<>();


    public ArrayList<String> getNulls() {
        return nulls;
    }

    public ArrayList<String> getDefaults() {
        return defaults;
    }

    public void addNotNulls(String y){
        this.nulls.add(y);
    }
    public void addDefaults(String y){
        this.defaults.add(y);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void addPrimaryKey(String pk){
        primaryKeys.add(pk);
    }
    public void addForeignKey(String fk){
        foreignKeys.add(fk);
    }
    public void addIndexList(String idx){
        indexList.add(idx);
    }
    public void addUnizueColumns(String uc){
        uniqueColumns.add(uc);
    }
    public void addColumns(String col){
        columns.add(col);
    }
    public void addColumnTypes(String type){
        columnTypes.add(type);
    }

    public ArrayList<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public ArrayList<String> getForeignKeys() {
        return foreignKeys;
    }

    public ArrayList<String> getIndexList() {
        return indexList;
    }

    public ArrayList<String> getUniqueColumns() {
        return uniqueColumns;
    }

    public ArrayList<String> getColumns() {
        return columns;
    }

    public ArrayList<String> getColumnTypes() {
        return columnTypes;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableName='" + tableName + '\'' +
                ", primaryKeys=" + primaryKeys +
                ", foreignKeys=" + foreignKeys +
                ", indexList=" + indexList +
                ", uniqueColumns=" + uniqueColumns +
                ", columns=" + columns +
                ", columnTypes=" + columnTypes +
                ", nulls=" + nulls +
                ", defaults=" + defaults +
                '}';
    }
}
