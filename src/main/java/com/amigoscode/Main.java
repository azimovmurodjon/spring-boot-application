package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
/**
 * It's syntactic sugar for combining other annotations that we'll look at in just a moment.
 * @SpringBootApplication is @Configuration, @EnableAutoConfiguration
 * and @ComponentScan annotations combined, configured with their default attributes.
 * We add this annotation just once, to the main class of our application.
 */

@RestController
/**
 * The @RestController annotation is a convenience syntax for @Controller
 * and @ResponseBody together. This indicates that the class is a controller,
 * and that all the methods in the marked class will return a JSON response.
 */
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/greet")
    /**
     * An abbreviated form of @RequestMapping specifically for
     * HTTP GET requests, which only takes an optional value argument, no method argument. The read in CRUD.
     */

    public GreetResponce greet(
            @RequestParam(value = "name", required = false) String name) {
        String greetMessage = name == null || name.isBlank() ? "Hello" : "Hello " + name;
        GreetResponce responce = new GreetResponce(
                greetMessage,
                List.of("Java", "Python", "JavaScript"),
                new Person("Alex", 28, 300_00)
        );
        return responce;
    }

    /**
     * @RestController The @RestController annotation is a convenience syntax for @Controller
     * and @ResponseBody together. This indicates that the class is a controller,
     * and that all the methods in the marked class will return a JSON response.
     * param GreetResponce
     */
    record Person(String nam, int age, double savings) {
    }

    record GreetResponce(
            String greet,
            List<String> favProgrammingLanguages,
            Person person
    ) {
    }

}
