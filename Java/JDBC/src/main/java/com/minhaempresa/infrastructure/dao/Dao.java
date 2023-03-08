package com.minhaempresa.infrastructure.dao;

import java.util.List;
/*
    CRUD (acrónimo do inglês Create, Read, Update and Delete).
    São as quatro operações básicas (criação, consulta, atualização e destruição de dados)

    Padrão Data Access Object (DAO):
          Usado para abstrair e encapsular (isolar) o acesso à fonte de dados.
          O DAO gerencia a conexão com a fonte de dados para obter e armazenar dados.

          https://www.oracle.com/technetwork/java/dataaccessobject-138824.html
 */
public interface Dao<T> {
    void insert(T object);
    void update(T object);
    void deleteById(Long id);
    default void deleteById(String id) {}
    T findById(Long id);
    default T findById(String id) {
        return null;
    }
    List<T> findAll();
}
