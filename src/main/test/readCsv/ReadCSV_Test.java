package readCsv;

import vo.Table;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/***테이블 명세서를 DDL.sql로 변환하는 java프로그램*******************
 * 데이터 샘플=====
 * (0)테이블 이름(1)(2)(3)(4)NC_F_DEVICE_WARNING_HIS_T
 * (0)테이블 설명(1)(2)(3)(4)장비 경고 이력 테이블
 * (0)PRIMARY KEY(1)(2)(3)(4)"DKEY(5) REG_DATETIME"
 * (0)FOREIGN KEY
 * (0)INDEX
 * (0)UNIQUE INDEX(1)(2)(3)(4)"(DKEY(5) REG_DATETIME)"
 * (0)NO(1)PK(2)AI(3)FK(4)NULL(5)컬럼 이름(6)TYPE(7)DEFAULT(8)설명(9)참조 테이블
 * (0)1(1)Y(2)(3)(4)(5)DKEY(6)"NUMBER(20(7)0)"(8)(9)장비KEY
 * (0)2(1)(2)(3)(4)Y(5)WAR_TYPE(6)"NUMBER(3(7)0)"(8)(9)"경고타입(1:속도(10)2:충돌(11)3:사이드브래이크(12)4:안전밸트(13)99:기타)"
 * (0)3(1)(2)(3)(4)Y(5)MESSAGE(6)VARCHAR2(1024)(7)(8)경고메시지
 * (0)4(1)(2)(3)(4)Y(5)WAR_COMMENT(6)VARCHAR2(1024)(7)(8)관리자 코멘트
 * (0)5(1)Y(2)(3)(4)(5)REG_DATETIME(6)VARCHAR2(14)(7)(8)등록일시
 * =====
 * 1.테이블 이름          -> 0번 인덱스가 "테이블 이름" 인 경우 4번 인덱스가 테이블 이름
 * 2.PRIMARY KEY        -> 0번 인덱스가 "PRIMARY KEY" 인 경우 4번 인덱스부터 PRIMARY KEY
 * 3.FOREIGN KEY        -> 0번 인덱스가 "FOREIGN KEY" 인 경우 4번 인덱스부터 FOREIGN KEY
 * 4.INDEX              -> 0번 인덱스가 "INDEX" 인 경우 4번 인덱스가 INDEX
 * 5.UNIQUE INDEX       -> 0번 인덱스가 "UNIQUE INDEX" 인 경우 4번 인덱스부터 UNIQUE INDEX ( 소괄호 제거 필요 )
 * 6.각 컬럼             -> 0번 인덱스가 "숫자" 인 경우 'Y' 표기 - 1번인덱스: PK, 2번인덱스:AI, 3번인덱스:FK, 4번인덱스:NOT NULL, 5번인덱스: 컬럼명, 6번인덱스:타입 (NUMBER(n,n) 인경우 7번인덱스까지), 7또는8번인덱스:DEFAULT
 * **********************/

public class ReadCSV_Test {

    static String relativeCsvDir = "csvDirectory";
    static String csvFileName = "tableDescription.csv";
    static String writeSqlDir = "sqlDirectory";
    static String sqlFileName = "tableDescription.sql";
    static List<Table> tableList = new ArrayList<>();
    static Table table = new Table();
    static int pkIndex = 0;


    public static void main(String[] args) {
        String csvFilePath = relativeCsvDir + "\\" + csvFileName;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 0) {
                    setVariables(values);
                } else {
                    tableToSQLState(table);
                    tableList.add(table);
                    //파일 쓰기
                }
            }
            tableList.add(table);
            System.out.println(tableList.toString());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setVariables(String[] csvLine) {

        String index0 = csvLine[0].trim();
        int csvLineLen = csvLine.length;

        if (index0.equals("테이블 이름") || index0.contains("이름")) {
            table = new Table();  //테이블 생성
            table.setTableName(csvLine[4].trim());

        } else if (index0.equals("PRIMARY KEY")) {
            for (int i = 4; i < csvLineLen; i++) {
                table.addPrimaryKey(csvLine[i].trim().replaceAll("\"", ""));
            }
        } else if (index0.equals("FOREIGN KEY")) {
            for (int i = 4; i < csvLineLen; i++) {
                table.addForeignKey(csvLine[i].trim());
            }
        } else if (index0.equals("INDEX")) {
            for (int i = 4; i < csvLineLen; i++) {
                table.addIndexList(csvLine[i].trim());
            }
        } else if (index0.equals("UNIQUE INDEX")) {
            for (int i = 4; i < csvLineLen; i++) {
                table.addUnizueColumns(csvLine[i].trim().replaceAll("[()]", ""));
            }
        } else if (index0.matches("\\d+?")) {
            //컬럼명, notnull
            table.addColumns(csvLine[5].trim());
            table.addNotNulls(csvLine[4].trim());

            //타입, default
            if (csvLine[6].contains("NUMBER(")) {
                table.addColumnTypes((csvLine[6].trim() + "," + csvLine[7].trim()).replaceAll("\"", ""));

                if (csvLine[8].contains("(")) {
                    table.addDefaults((csvLine[8] + "," + csvLine[9]).replaceAll("\"", ""));
                } else if (csvLine[8].contains(" ")) {
                    table.addDefaults("\'" + csvLine[8].trim() + "\'");
                } else {
                    table.addDefaults(csvLine[8].trim());
                }
            } else {
                table.addColumnTypes(csvLine[6].trim());
                if (csvLine[7].contains("(")) {
                    table.addDefaults((csvLine[7] + "," + csvLine[8]).replaceAll("\"", ""));
                } else if (csvLine[7].contains(" ")) {
                    table.addDefaults("\'" + csvLine[7].trim() + "\'");
                } else {
                    table.addDefaults(csvLine[7].trim());
                }
            }
        }

    }


    public static void tableToSQLState(Table table) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + table.getTableName() + "(\n");

        for (int i = 0; i < table.getColumns().size(); i++) {
            sb.append("\t");
            if (i > 0) sb.append(",");
            sb.append(table.getColumns().get(i) + " " + table.getColumnTypes().get(i));
            if (table.getDefaults().get(i) != "") {
                sb.append(" DEFAULT " + table.getDefaults().get(i));
            }
            if (!table.getNulls().get(i).contains("Y")) {
                sb.append(" NOT NULL");
            }
            sb.append("\n");
        }

        if (table.getPrimaryKeys().size() > 0) {
            sb.append(",\tCONSTRAINT PK_" + (pkIndex++) + " PRIMARY KEY(");
            for (int i = 0; i < table.getPrimaryKeys().size(); i++) {
                if (i > 0) sb.append(",");
                sb.append(table.getPrimaryKeys().get(i));
            }
            sb.append(")");
        }
        sb.append("\n);");

        System.out.println(sb.toString());
        System.out.println();

        sqlFileWrite(sb);

    }

    public static void sqlFileWrite(StringBuilder sb) {

        String sqlFilePath = writeSqlDir + "\\" + sqlFileName;
        File file = new File(sqlFilePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(sqlFilePath))) {
//            bf.write(sb.toString());
            bf.write("테스트");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
