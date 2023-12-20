import java.util.ArrayList;

public class Table {
    private String tableName;
    private ArrayList<String> primaryKeys = new ArrayList<>();
    private ArrayList<String> foreignKeys = new ArrayList<>();
    private ArrayList<String> indexList = new ArrayList<>();
    private ArrayList<String> uniqueColumns = new ArrayList<>();
    private ArrayList<String> columns = new ArrayList<>();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ArrayList<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(ArrayList<String> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public ArrayList<String> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(ArrayList<String> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    public ArrayList<String> getIndexList() {
        return indexList;
    }

    public void setIndexList(ArrayList<String> indexList) {
        this.indexList = indexList;
    }

    public ArrayList<String> getUniqueColumns() {
        return uniqueColumns;
    }

    public void setUniqueColumns(ArrayList<String> uniqueColumns) {
        this.uniqueColumns = uniqueColumns;
    }

    public ArrayList<String> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<String> columns) {
        this.columns = columns;
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
                '}';
    }
}
