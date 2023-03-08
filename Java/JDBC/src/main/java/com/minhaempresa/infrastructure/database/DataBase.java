package com.minhaempresa.infrastructure.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
/*
    Classe auxiliar para tratar das conexões com o banco de dados

    Padrão Singleton
        Usado quando queremos ter uma única instância do objeto no sistema.
 */
public class DataBase {

    private static Connection connection = null;

    private static Properties loadProperties() {
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/db.properties")) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        }
        catch (IOException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public static Connection getConnection() {
        if(connection == null) {
            try {
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                connection = DriverManager.getConnection(url, properties);
                connection.setAutoCommit(false);
            }
            catch(SQLException e) {
                throw new DataBaseException(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
            }
            catch (SQLException e) {
                throw new DataBaseException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DataBaseException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DataBaseException(e.getMessage());
            }
        }
    }

    public static void commit() {
        if(connection != null) {
            try {
                connection.commit();
            }
            catch (SQLException e) {
                throw new DataBaseException(e.getMessage());
            }
        }
    }

    public static void rollback() {
        if(connection != null) {
            try {
                connection.rollback();
            }
            catch (SQLException e) {
                throw new DataBaseException(e.getMessage());
            }
        }
    }
}