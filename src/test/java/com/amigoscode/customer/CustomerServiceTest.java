package com.amigoscode.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerDao customerDao;
    private CustomerService underTest;
    @BeforeEach
    void setUp() {
        AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerService(customerDao);
    }

    @Test
    void getAllCustomers() {
        // When
        underTest.getAllCustomers();
        // Then
        verify(customerDao).selectAllCustomers();
    }

    @Test
    void getCustomer() {
        // Given

        // When

        // Then
    }

    @Test
    void addCustomer() {
        // Given

        // When

        // Then
    }

    @Test
    void deleteCustomerById() {
        // Given

        // When

        // Then
    }

    @Test
    void updateCustomer() {
        // Given

        // When

        // Then
    }
}