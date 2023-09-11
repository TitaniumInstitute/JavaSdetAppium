package com.ti.frameworks.ddt.dataproviders;

import lombok.SneakyThrows;
import org.jdbc.DataBaseInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.ti.frameworks.ddt.config.Constants.QUERIES_FOLDER;

public class SQLArrayData {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private static Connection getDBConnection(String dbName) throws SQLException {
        return connection = DriverManager.getConnection(
                String.format("jdbc:mysql://%s:%s/%s?useSSL=false",
                        DataBaseInfo.DB_HOST.dbConn,DataBaseInfo.DB_PORT.dbConn, dbName),
                DataBaseInfo.DB_USERNAME.dbConn,DataBaseInfo.DB_PASSWORD.dbConn);
    }

    private static Statement getStatement(String dbName) throws SQLException {
        return statement = getDBConnection(dbName).createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    private static String readSqlFile(String sqlFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(QUERIES_FOLDER + sqlFile));
        StringBuilder sb = new StringBuilder();
        String line;

        while(Optional.ofNullable(line = br.readLine()).isPresent()){
            sb.append(line);
        }

        return  sb.toString();
    }

    public static ResultSet getResultSet(String dbName, String sqlFile) throws SQLException, IOException {
        return getStatement(dbName).executeQuery(readSqlFile(sqlFile));
    }

    @SneakyThrows
    public static void closeConnection() throws SQLException {
        if (Optional.ofNullable(statement).isPresent()) statement.close();
        if(Optional.ofNullable(connection).isPresent()) connection.close();
    }

   public static Object[][] getQueryTableArray(String dbName, String sqlFile) throws SQLException, IOException {
        resultSet = getResultSet(dbName,sqlFile);
        int colCount = resultSet.getMetaData().getColumnCount();
        resultSet.last();
        int rowCount = resultSet.getRow();
        resultSet.beforeFirst();

        Object[][] arr = new Object[rowCount][colCount];
        int row = 0;

        while (resultSet.next()){
            for (int i = 0; i < colCount; i++){
                System.out.println(resultSet.getObject(i+1));
                arr[row][i] = resultSet.getObject(i+1);
            }
            row ++;
        }
        return arr;
    }

    public static Object[][] getQueryTableArrayNew(String dbName, String sqlFile) throws SQLException, IOException {
        resultSet = getResultSet(dbName,sqlFile);
        int colCount = resultSet.getMetaData().getColumnCount();
        resultSet.last();

        resultSet.beforeFirst();

        List<LinkedHashMap<Object, Object>> result = new ArrayList<>();

        while (resultSet.next()){
            LinkedHashMap<Object, Object> resMap = new LinkedHashMap<>();
            for (int i = 0; i < colCount; i++){
                resMap.put(resultSet.getMetaData().getColumnName(i+1), resultSet.getObject(i + 1));
            }
            result.add(resMap);
        }
        return asTwoDimensionalArray(result);
    }

    private static Object[][] asTwoDimensionalArray(List<LinkedHashMap<Object, Object>> interimResults) {
        Object[][] results = new Object[interimResults.size()][1];
        int index = 0;
        for (LinkedHashMap<Object, Object> interimResult : interimResults) {
            results[index++] = new Object[] {interimResult};
        }
        return results;
    }
}
