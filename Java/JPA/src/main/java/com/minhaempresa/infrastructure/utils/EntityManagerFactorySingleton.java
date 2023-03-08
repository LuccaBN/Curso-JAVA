package com.minhaempresa.infrastructure.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
    Padrão Singleton
        Usado quando queremos ter uma única instância do objeto no sistema.
 */
public class EntityManagerFactorySingleton {
    private static EntityManagerFactory instance;

    public static EntityManagerFactory getInstance() {
        if(instance == null) {
            instance = Persistence.createEntityManagerFactory("persistence-unit-jpa");
        }
        return instance;
    }

    public static void closeEntityManagerFactory() {
        if(instance != null) {
            instance.close();
            instance = null;
        }
    }
}
