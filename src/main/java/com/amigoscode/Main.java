package com.amigoscode;

import com.amigoscode.customer.Customer;
import com.amigoscode.customer.CustomerRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * It's syntactic sugar for combining other annotations that we'll look at in just a moment.
 *
 * @SpringBootApplication is @Configuration, @EnableAutoConfiguration
 * and @ComponentScan annotations combined, configured with their default attributes.
 * We add this annotation just once, to the main class of our application.
 */

/**
 * The @RestController annotation is a convenience syntax for @Controller
 * and @ResponseBody together. This indicates that the class is a controller,
 * and that all the methods in the marked class will return a JSON response.
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {

        return args -> {
            Customer alex = new Customer(

                    "Alex",
                    "alex@gmail.com",
                    21
            );

            Customer jamila = new Customer(

                    "Jamila",
                    "jamila@gmail.com",
                    19
            );

            List<Customer>customers = List.of(alex, jamila);
//            customerRepository.saveAll(customers);
        };
    }
}


