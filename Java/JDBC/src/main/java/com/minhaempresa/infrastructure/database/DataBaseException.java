package com.minhaempresa.infrastructure.database;

/*
    A classe abaixo foi derivada da RuntimeException para que no programa não precise toda hora
    colocando o try...catch
 */
public class DataBaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataBaseException(String mensagem) {
        super(mensagem);
    }
}
