package com.minhaempresa.infrastructure.models;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private LocalDateTime date;
    private Integer amount;
    private Long pizzaId;
    private String telephone;
    private Double price;

    public Request() {}

    public Request(Integer amount, Long pizzaId, String telephone, Double price) {
        this.amount = amount;
        this.pizzaId = pizzaId;
        this.telephone = telephone;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Long pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", pizzaId=" + pizzaId +
                ", telephone='" + telephone + '\'' +
                ", price=" + price +
                '}';
    }
}
