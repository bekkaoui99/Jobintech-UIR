package com.hamzabekkaoui;

import com.hamzabekkaoui.exception.ResourceAlreadyExistException;
import com.hamzabekkaoui.model.*;
import com.hamzabekkaoui.repository.*;
import com.hamzabekkaoui.repository.jdbc.CustomerRepositoryJDBC;
import com.hamzabekkaoui.repository.jdbc.OrderRepositoryJDBC;
import com.hamzabekkaoui.repository.jdbc.PaymentRepositoryJDBC;
import com.hamzabekkaoui.repository.jdbc.ProductRepositoryJDBC;
import com.hamzabekkaoui.service.CustomerService;
import com.hamzabekkaoui.service.OrderService;
import com.hamzabekkaoui.service.PaymentService;
import com.hamzabekkaoui.service.ProductService;
import com.hamzabekkaoui.service.impl.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jobintechlearningjava";
        String userName = "hamza";
        String password = "password";
        JDBCManager jdbcManager = null;
        try {
            jdbcManager = new JDBCManager(url , userName ,password);

            // customer beans
            CustomerRepository customerRepository = new CustomerRepositoryJDBC(jdbcManager);
            CustomerService customerService = new CustomerServiceImpl(customerRepository);

           if(!customerRepository.existByUserName("hamza")){
               Customer customer = new Customer("hamza");
               Customer createdCustomer = customerService.create(customer);
               System.out.println("==================");
               System.out.println(createdCustomer);
               System.out.println("==================");
           }
            System.out.println("customer list :");
           customerService.getAllCustomers().forEach(System.out::println);


            // product beans
            ProductRepository productRepository = new ProductRepositoryJDBC(jdbcManager);
            ProductService productService = new ProductServiceImpl(productRepository);

            if(!productRepository.existByProductName("laptop")){
                Product product = new Product("laptop" , "description" , 2000.60 , 1000);
                Product createdProduct = productService.create(product);
                System.out.println("==================");
                System.out.println(createdProduct);
                System.out.println("==================");
            }
            System.out.println("product list :");
            productService.getAllProducts().forEach(System.out::println);


            // create and order
            OrderRepository orderRepository  = new OrderRepositoryJDBC(jdbcManager);
            PaymentRepository paymentRepository = new PaymentRepositoryJDBC(jdbcManager);
            PaymentService paymentService = new CardPaymentServiceImpl(paymentRepository);

            OrderService orderService = new OrderServiceImpl(orderRepository , paymentService);
            Product laptop = productRepository.findProductByProductName("laptop");
            Customer hamza = customerRepository.findCustomerByUserName("hamza");
            List<OrderItems> orderItems = new ArrayList<>();
            OrderItems orderItem = new OrderItems();
            orderItem.setProduct(laptop);
            orderItem.setQuantity(10);
            orderItem.setTotal(laptop.getPrice() * orderItem.getQuantity());
            orderItems.add(orderItem);
            Order order = new Order();
            order.setCustomer(hamza);
            order.setProducts(orderItems);
            Double total = 0.0;
            for (OrderItems items : orderItems){
                total += items.getTotal();
            }
            order.setTotal(total);
            PaymentDetails paymentDetails = new PaymentDetails();
            paymentDetails.setCardNumber("123333322111");
            paymentDetails.setExpiryDate("0524");
            paymentDetails.setAmount(1524.5);
            paymentDetails.setCvv("123456");

            Order createdOrder = orderService.create(order, paymentDetails);

            System.out.println("=========================");
            System.out.println(createdOrder);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
              jdbcManager.close();
        }
    }
}