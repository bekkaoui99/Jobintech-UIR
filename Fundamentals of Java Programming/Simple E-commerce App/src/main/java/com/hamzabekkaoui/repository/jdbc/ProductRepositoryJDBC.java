package com.hamzabekkaoui.repository.jdbc;

import com.hamzabekkaoui.exception.ResourceAlreadyExistException;
import com.hamzabekkaoui.exception.ResourceNotFoundException;
import com.hamzabekkaoui.model.Customer;
import com.hamzabekkaoui.model.Product;
import com.hamzabekkaoui.repository.JDBCManager;
import com.hamzabekkaoui.repository.ProductRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryJDBC implements ProductRepository {

    private final JDBCManager jdbcManager;
    public ProductRepositoryJDBC(JDBCManager jdbcManager) {
        this.jdbcManager = jdbcManager;
    }

    @Override
    public Product create(Product product) throws SQLException , ResourceAlreadyExistException {
        boolean existByProductName = existByProductName(product.getName());
        if (existByProductName) throw new ResourceAlreadyExistException("this product already exist :-(");
        String sql = "INSERT INTO product VALUES (?, ? , ?, ? , ?)";
        int executed = jdbcManager.executeUpdate(
                sql,
                product.getId(),
                product.getName(),
                product.getProductDescription(),
                product.getPrice(),
                product.getQuantity()
        );
        if(executed > 0) return product;
        else throw new IllegalArgumentException("something went wrong");
    }

    @Override
    public Product findProductByProductName(String productName) throws SQLException, ResourceNotFoundException {
       String sql = "select * from product where name = ?";
        ResultSet resultSet = jdbcManager.executeQuery(sql, productName);
        if(resultSet.next()){
            return new Product(
                 resultSet.getString("id"),
                 resultSet.getString("name"),
                 resultSet.getString("productDescription"),
                 resultSet.getDouble("price"),
                 resultSet.getInt("quantity")
            );
        }
        else throw new ResourceNotFoundException("product doesn't exist with this name :" + productName);
    }

    @Override
    public boolean existByProductName(String productName) throws SQLException {
        String sql = "select * from product where name = ?";
        ResultSet resultSet = jdbcManager.executeQuery(sql, productName);
        return resultSet.next();
    }

    @Override
    public List<Product> getAllProducts() throws SQLException{
        String sql = "select * from product";
        List<Product> products = new ArrayList<>();
        ResultSet resultSet = jdbcManager.executeQuery(sql);
        while (resultSet.next()){
            Product product = new Product(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("productDescription"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("quantity")
            );
            products.add(product);
        }
        return products;
    }
}
