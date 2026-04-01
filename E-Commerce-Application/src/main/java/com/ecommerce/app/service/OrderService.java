package com.ecommerce.app.service;

import com.ecommerce.app.dto.OrderDTO;
import jakarta.transaction.Transactional;

public interface OrderService {

    @Transactional
    OrderDTO placeOrder(String emailId, String paymentMethod, Long addressId,
                        String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage);

}
