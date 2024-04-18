package com.hamzabekkaoui.repository;

import java.sql.*;

public class JDBCManager {

    private Connection connection;

    public JDBCManager(String jdbcUrl, String username, String password) throws SQLException {
        this.connection = DriverManager.getConnection(jdbcUrl, username, password);
    }

    // Method to perform CRUD operations

    public Connection getConnection(){
        return connection;
    }

    public ResultSet executeQuery(String sql , Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeQuery();
    }

    public int executeUpdate(String sql , Object... params) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            return statement.executeUpdate();
        }
    }

    // Close the connection
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }


}
