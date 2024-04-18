package com.hamzabekkaoui.repository;

import com.hamzabekkaoui.exception.ResourceAlreadyExistException;
import com.hamzabekkaoui.exception.ResourceNotFoundException;
import com.hamzabekkaoui.model.Customer;
import com.hamzabekkaoui.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {

    Product create(Product product) throws SQLException , ResourceAlreadyExistException;
    Product findProductByProductName(String productName) throws SQLException, ResourceNotFoundException;
    boolean existByProductName(String productName) throws SQLException;
    List<Product> getAllProducts() throws SQLException;
}
