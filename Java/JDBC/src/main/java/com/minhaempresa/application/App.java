package com.minhaempresa.application;

import com.minhaempresa.Config;
import com.minhaempresa.application.services.CustomerService;
import com.minhaempresa.application.services.PizzaService;
import com.minhaempresa.application.services.RequestService;
import com.minhaempresa.infrastructure.database.DataBase;
import com.minhaempresa.infrastructure.models.Customer;
import com.minhaempresa.infrastructure.models.Pizza;
import com.minhaempresa.infrastructure.models.Request;
import java.util.List;
/*
    Java Database Connectivity ou JDBC é um conjunto de classes e interfaces escritas em Java
    que fazem o envio de instruções SQL para qualquer banco de dados relacional.

    Está classe representa uma aplicação fazendo uso dos serviços disponíveis.

    Esta classe desconhece como as informações são persistidas (mantidas, guardadas),

    ela não tem conhecimento algum do banco de dados, fornecedor ou mesmo se a informação é

    gravada num banco.
*/
public class App {
    public static void main (String [] args) {
            Config.createTables(DataBase.getConnection());

            PizzaService pizzaService = new PizzaService();
            Pizza pizza = new Pizza("Muzzarela", 30.50);
            pizzaService.pizzaRegistration(pizza);

            CustomerService customerService = new CustomerService();
            Customer customer = new Customer("11999999999", "Marcos", "Av. Paulista, 1578");
            customerService.customerRegistration(customer);

            RequestService requestService = new RequestService();
            Request request = new Request(2, pizza.getId(), customer.getTelephone(), pizza.getPrice());
            requestService.requestRegistration(request);

            pizza.setPrice(33.89);
            pizzaService.update(pizza);

            pizza = new Pizza("Calabreza", 30.50);
            pizzaService.pizzaRegistration(pizza);
            pizzaService.deleteById(pizza.getId());

            List<Pizza> pizzas = pizzaService.findAll();
            pizzas.forEach(System.out::println);

            List<Customer> customers = customerService.findAll();
            customers.forEach(System.out::println);

            List<Request> requests = requestService.findAll();
            requests.forEach(System.out::println);

            Config.dropTables(DataBase.getConnection());
    }
}