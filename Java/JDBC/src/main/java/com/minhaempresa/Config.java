package com.minhaempresa;

import com.minhaempresa.infrastructure.database.DataBase;
import com.minhaempresa.infrastructure.database.DataBaseException;
import java.sql.*;
/*
    Esta classe cria as tabelas necessárias para este projeto.
    Foi criada apenas para auxiliar o curso.
 */
public class Config {
    public static void createTables(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE tb_pizza (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) NOT NULL, price NUMERIC(18,2) NOT NULL)");
            statement.execute("CREATE TABLE tb_customer (telephone VARCHAR(11) PRIMARY KEY, name VARCHAR(255) NOT NULL, address VARCHAR(255) NOT NULL)");
            statement.execute("CREATE TABLE tb_request (id BIGINT AUTO_INCREMENT PRIMARY KEY, date TIMESTAMP NOT NULL DEFAULT NOW(), amount INTEGER NOT NULL, pizza_id INTEGER NOT NULL, telephone varchar(11) NOT NULL, price NUMERIC(18,2) NOT NULL)");
            statement.execute("ALTER TABLE tb_request ADD FOREIGN KEY (pizza_id)  REFERENCES tb_pizza (id)");
            statement.execute("ALTER TABLE tb_request ADD FOREIGN KEY (telephone)  REFERENCES tb_customer (telephone)");
            DataBase.closeStatement(statement);
            statement = null;
        }
        catch (SQLException e) {
            throw new DataBaseException("Unexpected error! " + e.getMessage());
        }
        finally {
            DataBase.closeStatement(statement);
        }
    }

    public static void dropTables(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("DROP TABLE tb_request");
            statement.execute("DROP TABLE tb_pizza");
            statement.execute("DROP TABLE tb_customer");
            DataBase.closeStatement(statement);
            statement = null;
        }
        catch (SQLException e) {
            throw new DataBaseException("Rollback " + e.getMessage());
        }
        finally {
            DataBase.closeStatement(statement);
            DataBase.closeConnection();
        }
    }
}