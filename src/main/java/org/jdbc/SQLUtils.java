package org.jdbc;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class SQLUtils {
    //public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private final static String DATA_FOLDER = System.getProperty("user.dir") + "/src/main/resources/queries/";
    private static Connection connection;
    private static Statement statement;
    //private static ResultSet resultSet;

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
        BufferedReader br = new BufferedReader(new FileReader(DATA_FOLDER + sqlFile));
        StringBuilder sb = new StringBuilder();
        String line;

       /*while((line = br.readLine()) != null){
            sb.append(line);
        }*/

        while(Optional.ofNullable(line = br.readLine()).isPresent()){
            sb.append(line);
        }

        return  sb.toString();
    }

    public static ResultSet getResultSet(String dbName, String sqlFile) throws SQLException, IOException {
        return getStatement(dbName).executeQuery(readSqlFile(sqlFile));
    }

    @SneakyThrows
    public static void closeConnection() {
        if (Optional.ofNullable(statement).isPresent()) statement.close();
        if(Optional.ofNullable(connection).isPresent()) connection.close();
    }

   /* public static Object[][] getQueryTableArray(String dbName, String sqlFile) throws SQLException, IOException {
        resultSet = getResultSet(dbName,sqlFile);
        int colCount = resultSet.getMetaData().getColumnCount();
        resultSet.last();
        int rowCount = resultSet.getRow();
        resultSet.beforeFirst();

        Object[][] arr = new Object[rowCount][colCount];
        int row = 0;

        while (resultSet.next()){
            for (int i = 0; i < colCount; i++){
                arr[row][i] = resultSet.getObject(i+1);
            }
            row ++;
        }
        return arr;
    }*/
}
