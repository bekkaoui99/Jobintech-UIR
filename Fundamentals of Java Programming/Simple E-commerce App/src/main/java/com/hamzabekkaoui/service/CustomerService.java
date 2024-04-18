package com.hamzabekkaoui.service;

import com.hamzabekkaoui.exception.ResourceAlreadyExistException;
import com.hamzabekkaoui.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {
    Customer create(Customer customer) throws SQLException, ResourceAlreadyExistException;
    List<Customer> getAllCustomers() throws SQLException;
}
