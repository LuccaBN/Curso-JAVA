package com.minhaempresa.application;

import com.minhaempresa.application.services.CustomerService;
import com.minhaempresa.application.services.PizzaService;
import com.minhaempresa.application.services.RequestService;
import com.minhaempresa.infrastructure.models.Customer;
import com.minhaempresa.infrastructure.models.Pizza;
import com.minhaempresa.infrastructure.models.Request;
import com.minhaempresa.infrastructure.utils.EntityManagerFactorySingleton;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
/*
    Java Persistence API (JPA) é a especificação padrão da plataforma JavaEE (pacote javax.persistence)
    para mapeamento objeto-relacional e persistência de dados

    Para trabalhar com JPA é preciso incluir no projeto uma implementação da API Ex.: Hibernate

    Um objeto EntityManager encapsula uma conexão com a base de dados e serve para efetuar operações de
    acesso a dados (criação, consulta, atualização e destruição de dados)
    nas entidades (tabelas) por ele monitoradas em um mesmo contexto de persistência.

    É comum manter uma única instância de EntityManager para cada thread do sistema (no caso
    de aplicações web, para cada requisição ao sistema).

    EntityManagerFactory
    https://docs.oracle.com/javaee/7/api/javax/persistence/EntityManagerFactory.html
    Um objeto EntityManagerFactory é utilizado para instanciar objetos EntityManager.
    É comum manter uma instância única de EntityManagerFactory para toda aplicação.

    Está classe representa uma aplicação fazendo uso dos serviços disponíveis.
 */
public class App {
    public static void main (String [] args) {
        PizzaService pizzaService = new PizzaService();
        Pizza pizza = new Pizza("Muzzarela", 30.50);
        pizzaService.pizzaRegistration(pizza);

        CustomerService customerService = new CustomerService();
        Customer customer = new Customer("11999999999", "Marcos", "Av. Paulista, 1578");
        customerService.customerRegistration(customer);

        RequestService requestService = new RequestService();
        Request request = new Request(LocalDateTime.now(ZoneId.of("UTC")), 2, pizza.getPrice(), pizza, customer);
        requestService.requestRegistration(request);

        pizza.setPrice(33.89);
        pizzaService.update(pizza);

        pizza = new Pizza("Calabreza", 30.50);
        pizzaService.pizzaRegistration(pizza);
        pizzaService.deleteById(pizza);

        List<Pizza> pizzas = pizzaService.findAll();
        pizzas.forEach(System.out::println);

        List<Customer> customers = customerService.findAll();
        customers.forEach(System.out::println);

        List<Request> requests = requestService.findAll();
        requests.forEach(System.out::println);

        pizzaService.closeEntityManager();
        customerService.closeEntityManager();
        requestService.closeEntityManager();
        EntityManagerFactorySingleton.closeEntityManagerFactory();
    }
}
