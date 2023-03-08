package com.minhaempresa.infrastructure.dao.impl;

import com.minhaempresa.infrastructure.dao.Dao;
import com.minhaempresa.infrastructure.database.DataBase;
import com.minhaempresa.infrastructure.database.DataBaseException;
import com.minhaempresa.infrastructure.database.DataBaseIntegrityException;
import com.minhaempresa.infrastructure.models.Request;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDao implements Dao<Request> {
    private final Connection connection;

    public RequestDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Request request) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO tb_request(amount, pizza_id, telephone, price) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setInt(1,request.getAmount());
            preparedStatement.setLong(2,request.getPizzaId());
            preparedStatement.setString(3, request.getTelephone());
            preparedStatement.setDouble(4, request.getPrice());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    request.setId(resultSet.getLong(1));
                }
            }
            else {
                throw new DataBaseException("Unexpected error! No rows affected!");
            }
        }
        catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
        finally {
            DataBase.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Request request) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE tb_request SET amount = ?, pizza_id = ?, telephone_id, = ?, price = ? WHERE id = ?"
            );
            preparedStatement.setInt(1, request.getAmount());
            preparedStatement.setLong(2,request.getPizzaId());
            preparedStatement.setString(3, request.getTelephone());
            preparedStatement.setDouble(4, request.getPrice());
            preparedStatement.setLong(5, request.getId());
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
    public void deleteById(Long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM tb_request WHERE id = ?"
            );
            preparedStatement.setLong(1, id);
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
    public Request findById(Long id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT id, date, amount, pizza_id, telephone, price FROM tb_request WHERE id = ?"
            );
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Request request = new Request();
                request.setId(resultSet.getLong("id"));
                request.setDate(resultSet.getTimestamp("date").toLocalDateTime());
                request.setAmount(resultSet.getInt("amount"));
                request.setPizzaId(resultSet.getLong("pizza_id"));
                request.setTelephone(resultSet.getString("telephone"));
                request.setPrice(resultSet.getDouble("price"));
                return request;
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
    public List<Request> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT id, date, amount, pizza_id, telephone, price FROM tb_request ORDER BY id");
            resultSet = preparedStatement.executeQuery();
            List<Request> list = new ArrayList<>();
            while (resultSet.next()) {
                Request request = new Request();
                request.setId(resultSet.getLong("id"));
                request.setDate(resultSet.getTimestamp("date").toLocalDateTime());
                request.setAmount(resultSet.getInt("amount"));
                request.setPizzaId(resultSet.getLong("pizza_id"));
                request.setTelephone(resultSet.getString("telephone"));
                request.setPrice(resultSet.getDouble("price"));
                list.add(request);
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