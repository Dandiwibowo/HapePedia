package com.dao;

import java.util.List;

import com.entity.PaymentMethod;

public interface PaymentMethodDAO {
    public List<PaymentMethod> getAllPaymentMethods() throws Exception;
    public PaymentMethod getPaymentMethod(int id) throws Exception;
    public int addPaymentMethod(PaymentMethod payment) throws Exception;
    public int updatePaymentMethod(PaymentMethod payment) throws Exception;
    public int deletePaymentMethod(int id) throws Exception;
}
