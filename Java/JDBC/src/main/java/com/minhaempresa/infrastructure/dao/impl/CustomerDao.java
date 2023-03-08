package com.minhaempresa.infrastructure.dao.impl;

import com.minhaempresa.infrastructure.database.DataBase;
import com.minhaempresa.infrastructure.database.DataBaseException;
import com.minhaempresa.infrastructure.dao.Dao;
import com.minhaempresa.infrastructure.database.DataBaseIntegrityException;
import com.minhaempresa.infrastructure.models.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements Dao<Customer> {
    private final Connection connection;

    public CustomerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Customer customer) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
            "INSERT INTO tb_customer(telephone, name, address) VALUES (? , ?, ?)"
            );
            preparedStatement.setString(1,customer.getTelephone());
            preparedStatement.setString(2,customer.getName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
        finally {
            DataBase.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Customer customer) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
            "UPDATE tb_customer SET name = ?, address = ? WHERE telephone = ?"
            );
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2,customer.getAddress());
            preparedStatement.setString(3, customer.getTelephone());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
        finally {
            DataBase.closeStatement(preparedStatement);
        }
    }

    @Override
    public void deleteById(Long id) {}

    @Override
    public void deleteById(String id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
            "DELETE FROM tb_customer WHERE telephone = ?"
            );
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataBaseIntegrityException(e.getMessage());
        }
        finally {
            DataBase.closeStatement(preparedStatement);
        }
    }

    @Override
    public Customer findById(String id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT telephone, name, address FROM tb_customer WHERE telephone = ?"
            );
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setTelephone(resultSet.getString("telephone"));
                customer.setName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                return customer;
            }
            return null;
        }
        catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
        finally {
            DataBase.closeResultSet(resultSet);
            DataBase.closeStatement(preparedStatement);
        }
    }

    @Override
    public Customer findById(Long id) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT telephone, name, address FROM tb_customer ORDER BY name");
            resultSet = preparedStatement.executeQuery();
            List<Customer> list = new ArrayList<>();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setTelephone(resultSet.getString("telephone"));
                customer.setName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                list.add(customer);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
        finally {
            DataBase.closeResultSet(resultSet);
            DataBase.closeStatement(preparedStatement);
        }
    }
}