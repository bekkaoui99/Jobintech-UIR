package com.hamzabekkaoui.repository;

import com.hamzabekkaoui.exception.ResourceAlreadyExistException;
import com.hamzabekkaoui.exception.ResourceNotFoundException;
import com.hamzabekkaoui.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository {

    Customer create(Customer customer) throws SQLException, ResourceAlreadyExistException;
    Customer findCustomerByUserName(String userName) throws SQLException, ResourceNotFoundException;
    boolean existByUserName(String userName) throws SQLException;
    List<Customer> getAllCustomers() throws SQLException;
}
