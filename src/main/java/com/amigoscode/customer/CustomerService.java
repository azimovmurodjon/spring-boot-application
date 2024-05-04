package com.amigoscode.customer;

import com.amigoscode.exception.DuplicateResourceException;
import com.amigoscode.exception.RequestValidationException;
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

    public List<Customer> getAllCustomers() {
        return customerDAO.selectAllCustomers();
    }

    public Customer getCustomer(Integer customerId) {
        return customerDAO.selectCustomerById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "customer with id [%s] is not found".formatted(customerId)
                ));
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {

        String email = customerRegistrationRequest.email();
        String name = customerRegistrationRequest.name();
        Integer age = customerRegistrationRequest.age();

        if (customerDAO.existsPersonWithEmail(email)) {
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

    public void deleteCustomerById(Integer customerId) {
        if (!customerDAO.existsPersonWithId(customerId)) {
            throw new RequestValidationException(
                    "customer with id [%s] is not found".formatted(customerId)
            );
        }
        customerDAO.deleteCustomerById(customerId);

    }

    public void updateCustomer(
            CustomerUpdateRequest updateRequest,
            Integer customerId
    ) {

        Customer customer = getCustomer(customerId);

        boolean changes = false;

        if (updateRequest.name() != null && !updateRequest.name().equals(customer.getName())){
            customer.setName(updateRequest.name());
            changes = true;
        }

        if (updateRequest.age() != null && !updateRequest.age().equals(customer.getAge())){
            customer.setAge(updateRequest.age());
            changes = true;
        }

        if (updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())){
           if (customerDAO.existsPersonWithEmail(updateRequest.email())){
               throw new DuplicateResourceException(
                       "email already taken"
               );
           }
            customer.setEmail(updateRequest.email());
            changes = true;
        }
        if (!changes){
            throw new RequestValidationException("no data changes found");
        }
        customerDAO.updateCustomer(customer);
    }
}
