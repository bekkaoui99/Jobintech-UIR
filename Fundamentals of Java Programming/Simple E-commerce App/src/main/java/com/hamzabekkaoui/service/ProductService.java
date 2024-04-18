package com.hamzabekkaoui.service;

import com.hamzabekkaoui.exception.ResourceAlreadyExistException;
import com.hamzabekkaoui.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    Product create(Product product) throws SQLException , ResourceAlreadyExistException;
    List<Product> getAllProducts()throws SQLException;;
}
