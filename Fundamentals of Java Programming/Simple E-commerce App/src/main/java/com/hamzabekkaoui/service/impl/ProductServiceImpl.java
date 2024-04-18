package com.hamzabekkaoui.service.impl;

import com.hamzabekkaoui.exception.ResourceAlreadyExistException;
import com.hamzabekkaoui.model.Product;
import com.hamzabekkaoui.repository.ProductRepository;
import com.hamzabekkaoui.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @Override
    public Product create(Product product) throws SQLException , ResourceAlreadyExistException {
        return productRepository.create(product);
    }

    @Override
    public List<Product> getAllProducts() throws SQLException  {
        return productRepository.getAllProducts();
    }
}
