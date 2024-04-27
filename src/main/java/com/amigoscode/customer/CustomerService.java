package com.amigoscode.customer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers(){
        return customerDAO.selectAllCustomers();
    }

    public Customer getCustomer(Integer customerId){
        return customerDAO.selectCustomerById(customerId)
                .orElseThrow(() -> new IllegalArgumentException(
                                "customer with id [%s} is not found".formatted(customerId)
                ));
    }
}
