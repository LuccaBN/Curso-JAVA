package com.minhaempresa.application.services;

import com.minhaempresa.infrastructure.models.*;
import com.minhaempresa.infrastructure.utils.EntityManagerFactorySingleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/*
    Esta classe representa os serviços disponíveis para o cadastro da pizza.
 */
public class PizzaService {
    EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public Pizza pizzaRegistration(Pizza pizza) {
        entityManager.getTransaction().begin();
        entityManager.persist(pizza);
        entityManager.getTransaction().commit();
        return pizza;
    }

    public Pizza findById(Long id) {
        return entityManager.find(Pizza.class, id);
    }

    public List<Pizza> findAll() {
        Query query = entityManager.createQuery("from Pizza");
        return query.getResultList();
    }

    public Pizza update(Pizza pizza) {
        entityManager.getTransaction().begin();
        entityManager.persist(pizza);
        entityManager.getTransaction().commit();
        return pizza;
    }

    public void deleteById(Pizza pizza) {
        entityManager.getTransaction().begin();
        entityManager.remove(pizza);
        entityManager.getTransaction().commit();
        //entityManager.getTransaction().rollback();
    }

    public void closeEntityManager() {
        entityManager.close();
    }
}
