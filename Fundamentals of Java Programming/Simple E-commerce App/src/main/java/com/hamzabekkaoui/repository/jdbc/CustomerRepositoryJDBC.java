package com.hamzabekkaoui.repository.jdbc;

import com.hamzabekkaoui.exception.ResourceAlreadyExistException;
import com.hamzabekkaoui.exception.ResourceNotFoundException;
import com.hamzabekkaoui.model.Customer;
import com.hamzabekkaoui.repository.CustomerRepository;
import com.hamzabekkaoui.repository.JDBCManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryJDBC implements CustomerRepository {

    private final JDBCManager jdbcManager;
    public CustomerRepositoryJDBC(JDBCManager jdbcManager) {
        this.jdbcManager = jdbcManager;
    }

    @Override
    public Customer create(Customer customer) throws SQLException, ResourceAlreadyExistException {
        boolean existByUserName = existByUserName(customer.getUserName());
        if(existByUserName) throw new ResourceAlreadyExistException("customer already exist with this userName try to use another one ...");

        String sql = "INSERT INTO customer (id, userName) VALUES (?, ?);";
        int executed = jdbcManager.executeUpdate(sql , customer.getId() , customer.getUserName());
        if(executed > 0){
            System.out.println("customer is created successfully");
            return customer;
        }
        else throw new IllegalArgumentException("something went wrong");
    }

    @Override
    public Customer findCustomerByUserName(String userName) throws SQLException, ResourceNotFoundException {
        String sql = "select * from customer where userName = ?";
        ResultSet resultSet = jdbcManager.executeQuery(sql, userName);
        if(resultSet.next()){
            return new Customer(resultSet.getString("id"),
                    resultSet.getString("userName"));

        }
        else throw new ResourceNotFoundException("customer doesn't exist with this name :" + userName);
    }

    @Override
    public boolean existByUserName(String userName) throws SQLException {
        String sql = "select * from customer where userName = ?";
        ResultSet resultSet = jdbcManager.executeQuery(sql, userName);
        return resultSet.next();
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException{
        String sql = "SELECT * FROM customer";
        List<Customer> customers = new ArrayList<>();
        ResultSet resultSet = jdbcManager.executeQuery(sql);
        while (resultSet.next()) {
            String id = resultSet.getString("id"); // Assuming there's a column named "id"
            String name = resultSet.getString("userName"); // Assuming there's a column named "name"
            Customer  customer = new Customer(id , name);
            customers.add(customer);
        }
        return customers;
    }
}
