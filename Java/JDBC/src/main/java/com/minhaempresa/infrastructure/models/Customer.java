package com.minhaempresa.infrastructure.models;

import java.io.Serializable;
import java.util.Objects;
/*
    Esta classe representa o modelo da entidade

    O modelo de entidade deve:

        - Implementar a interface Serializable;
        - Ter os atributos da entidade que representa;
        - Ter construtores;
        - Ter métodos Gettters e Setters;
        - Ter métodos hascode e equals;
        - Ter o método toString;
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private String telephone;
    private String name;
    private String address;

    public Customer() {}

    public Customer(String telephone, String name, String address) {
        this.telephone = telephone;
        this.name = name;
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(telephone, customer.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telephone);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "telephone='" + telephone + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}