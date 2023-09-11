package org.jdbc;

public enum DataBaseInfo {
    DB_HOST(PropertyManager.getInstance().getProperty("DBHost")),
    DB_PORT(PropertyManager.getInstance().getProperty("DBPort")),
    DB_USERNAME(PropertyManager.getInstance().getProperty("DBUserName")),
    DB_PASSWORD(PropertyManager.getInstance().getProperty("DBPassword"));

    public final String dbConn;
    DataBaseInfo(String dbConn) {
        this.dbConn = dbConn;
    }
}
