package com.hamzabekkaoui.repository;

import com.hamzabekkaoui.model.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentRepository {
    Payment create(Payment payment) throws SQLException;
}
