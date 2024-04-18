package com.hamzabekkaoui.service.impl;

import com.hamzabekkaoui.exception.ResourceAlreadyExistException;
import com.hamzabekkaoui.model.Customer;
import com.hamzabekkaoui.repository.CustomerRepository;
import com.hamzabekkaoui.service.CustomerService;

import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    @Override
    public Customer create(Customer customer) throws SQLException, ResourceAlreadyExistException {
        return customerRepository.create(customer);
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        return customerRepository.getAllCustomers();
    }
}
