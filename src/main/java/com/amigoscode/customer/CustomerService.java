package com.amigoscode.customer;

import com.amigoscode.exception.ResourceNotFound;
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
                .orElseThrow(() -> new ResourceNotFound(
                                "customer with id [%s] is not found".formatted(customerId)
                ));
    }
}
