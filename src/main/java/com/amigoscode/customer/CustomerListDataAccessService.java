package com.amigoscode.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDAO {

    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer alex = new Customer(

                1,
                "Alex",
                "alex@gmail.com",
                21
        );
        customers.add(alex);

        Customer jamila = new Customer(

                2,
                "Jamila",
                "jamila@gmail.com",
                19
        );
        customers.add(jamila);

    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer customerId) {
        return  customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return customers.stream()
                .anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public boolean existsPersonWithId(Integer Id) {
        return customers.stream()
                .anyMatch(c -> c.getId().equals(Id));
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst()
                .ifPresent(customers :: remove);
//                  or use this
//                .ifPresent(o -> customers.remove(o));
    }

    @Override
    public void updateCustomer(Customer update) {
        customers.add(update);
    }

}
