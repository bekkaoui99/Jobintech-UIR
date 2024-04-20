package com.hamzabekkaoui;

import com.hamzabekkaoui.model.Customer;
import com.hamzabekkaoui.model.Order;
import com.hamzabekkaoui.model.Product;
import com.hamzabekkaoui.service.OrderService;
import com.hamzabekkaoui.service.PaymentService;
import com.hamzabekkaoui.service.impl.CardPaymentService;
import com.hamzabekkaoui.service.impl.CashPaymentService;
import com.hamzabekkaoui.service.impl.OrderServiceImpl;
import com.hamzabekkaoui.service.impl.PaypalPaymentService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        String filePath = "dependencyInjection.txt";
        String paymentMethod = "";

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            String line = bufferedReader.readLine();
            if (line != null) {
                paymentMethod= line;

            } else {
                System.out.println("File is empty");
            }
            bufferedReader.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Customer customer = new Customer("hamza bekkaoui");
        Product product = new Product();
        product.setName("laptop");
        product.setDescription("description");
        product.setPrice(1000.5);
        product.setQuantity(100);

        Order order = new Order();
        List<Product> products = new ArrayList<>();
        products.add(product);
        order.setProducts(products);
        order.setCustomer(customer);

        // create an object from text file
        PaymentService paymentService;
        Class<?> classPaymentService = Class.forName(paymentMethod);
        paymentService = (PaymentService)  classPaymentService.newInstance();

        OrderService orderService = new OrderServiceImpl(paymentService);
        orderService.order(order);




    }
}