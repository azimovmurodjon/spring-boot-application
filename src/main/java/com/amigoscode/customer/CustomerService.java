package com.amigoscode.customer;

import com.amigoscode.exception.DuplicateResourceException;
import com.amigoscode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService(@Qualifier("jpa") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers(){
        return customerDAO.selectAllCustomers();
    }

    public Customer getCustomer(Integer customerId){
        return customerDAO.selectCustomerById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                                "customer with id [%s] is not found".formatted(customerId)
                ));
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest){

        String email = customerRegistrationRequest.email();
        String name = customerRegistrationRequest.name();
        Integer age = customerRegistrationRequest.age();

        if (customerDAO.existsPersonWithEmail(email)){
                throw new DuplicateResourceException(
                        "email already taken"
                );
            }

        Customer customer = new Customer(
                name,
                email,
                age
        );

        customerDAO.insertCustomer(customer);
    }
}
