package com.minhaempresa.infrastructure.dao;

import com.minhaempresa.infrastructure.dao.impl.*;
import com.minhaempresa.infrastructure.database.DataBase;
/*
    Padrão Factory que é uma classe que cria objetos.
    O objetivo é facilitar a criação de objetos
 */
public class DaoFactory {
    public static Dao createPizzaDao() {
        return new PizzaDao(DataBase.getConnection());
    }

    public static Dao createCustomerDao() { return new CustomerDao(DataBase.getConnection()); }

    public static Dao createRequestDao() {
        return new RequestDao(DataBase.getConnection());
    }
}
