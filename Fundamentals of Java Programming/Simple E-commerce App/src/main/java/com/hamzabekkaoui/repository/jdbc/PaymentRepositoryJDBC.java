package com.hamzabekkaoui.repository.jdbc;

import com.hamzabekkaoui.model.Payment;
import com.hamzabekkaoui.repository.JDBCManager;
import com.hamzabekkaoui.repository.PaymentRepository;

import java.sql.SQLException;

public class PaymentRepositoryJDBC implements PaymentRepository {

    private final JDBCManager jdbcManager;
    public PaymentRepositoryJDBC(JDBCManager jdbcManager) {
        this.jdbcManager = jdbcManager;
    }


    @Override
    public Payment create(Payment payment) throws SQLException {
        String sql = "INSERT INTO payment VALUES (?,?,?,?)";
        int executed = jdbcManager.executeUpdate(
                sql,
                payment.getId(),
                payment.getOrder().getId(),
                payment.getAmount(),
                payment.getPaymentDate()
        );
        if (executed > 0) return payment;
        else throw new IllegalArgumentException("something went wrong");
    }
}
