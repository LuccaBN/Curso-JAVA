package com.minhaempresa.application.services;

import com.minhaempresa.infrastructure.dao.Dao;
import com.minhaempresa.infrastructure.dao.DaoFactory;
import com.minhaempresa.infrastructure.database.DataBase;
import com.minhaempresa.infrastructure.models.Pizza;
import java.util.List;
/*
    Esta classe representa os serviços disponíveis para o cadastro da pizza.
 */
public class PizzaService {
    private Dao<Pizza> dao = DaoFactory.createPizzaDao();

    public Pizza pizzaRegistration(Pizza pizza) {
        dao.insert(pizza);
        DataBase.commit();
        return  pizza;
    }

    public Pizza findById(Long id) { return dao.findById(id); }

    public List<Pizza> findAll() {
        return dao.findAll();
    }

    public Pizza update(Pizza pizza) {
        dao.update(pizza);
        DataBase.commit();
        return pizza;
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
        DataBase.commit();
        //DataBase.rollback();
    }
}
