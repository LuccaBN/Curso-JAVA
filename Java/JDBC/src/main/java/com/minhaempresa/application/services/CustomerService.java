package com.minhaempresa.application.services;

import com.minhaempresa.infrastructure.dao.Dao;
import com.minhaempresa.infrastructure.dao.DaoFactory;
import com.minhaempresa.infrastructure.database.DataBase;
import com.minhaempresa.infrastructure.models.Customer;
import java.util.List;
/*
    Esta classe representa os serviços disponíveis para o cadastro do cliente.
 */
public class CustomerService {
    private Dao<Customer> dao = DaoFactory.createCustomerDao();

    public Customer customerRegistration(Customer customer) {
        dao.insert(customer);
        DataBase.commit();
        return  customer;
    }

    public Customer findById(String id) { return dao.findById(id); }

    public List<Customer> findAll() {
        return dao.findAll();
    }

    public Customer update(Customer customer) {
        dao.update(customer);
        DataBase.commit();
        return customer;
    }

    public void deleteById(String id) {
        dao.deleteById(id);
        DataBase.commit();
    }
}
