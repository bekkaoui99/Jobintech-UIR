package com.hamzabekkaoui.cvcrudapp.repositories;

import java.sql.*;

public class JDBCManager {


    private Connection connection;

    public JDBCManager(String jdbcUrl, String username, String password) throws SQLException {
        this.connection = DriverManager.getConnection(jdbcUrl, username, password);
    }

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

    public int executeUpdate(boolean returnGeneratedKeys, String sql,  Object... params) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS)) {

            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            int affectedRows = statement.executeUpdate();

            if (returnGeneratedKeys) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Return the generated ID
                    } else {
                        throw new SQLException("failed, No ID obtained.");
                    }
                }
            } else {
                return affectedRows; // Return the number of affected rows
            }
        }
    }

    // Close the connection
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
