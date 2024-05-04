package com.amigoscode.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(path = "/{customerId}")
    public Customer getCustomer(
            @PathVariable("customerId") Integer customerId) {
            return customerService.getCustomer(customerId);
    }

    @PostMapping
    public void RegisterCustomer(
            @RequestBody CustomerRegistrationRequest request){
        customerService.addCustomer(request);
    }

    @DeleteMapping(path = "/{customerId}")
    public void DeleteCustomer(
            @PathVariable("customerId") Integer customerId){
         customerService.deleteCustomerById(customerId);
    }

    @PutMapping("{customerId}")
    public void UpdateCustomer(
            @PathVariable("customerId") Integer customerId,
            @RequestBody CustomerUpdateRequest updateRequest) {
        customerService.updateCustomer(updateRequest, customerId);
    }


}
