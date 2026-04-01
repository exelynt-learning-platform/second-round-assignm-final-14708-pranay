package com.ecommerce.app.controller;

import com.ecommerce.app.dto.OrderDTO;
import com.ecommerce.app.dto.StripePaymentDTO;
import com.ecommerce.app.dto.request.OrderRequestDTO;
import com.ecommerce.app.service.OrderService;
import com.ecommerce.app.service.StripeService;
import com.ecommerce.app.util.AuthUtil;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private StripeService stripeService;

    @PostMapping("/order/users/payments/{paymentMethod}")
    public ResponseEntity<OrderDTO> orderProducts(@RequestBody OrderRequestDTO orderRequest,
                                                  @PathVariable("paymentMethod") String paymentMethod) {

        String emailId = authUtil.loggedInEmail();

        OrderDTO orderDTO = orderService.placeOrder(
                emailId,
                paymentMethod,
                orderRequest.getAddressId(),
                orderRequest.getPgName(),
                orderRequest.getPgPaymentId(),
                orderRequest.getPgStatus(),
                orderRequest.getPgResponseMessage()
        );
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }

    @PostMapping("/order/stripe-client-secret")
    public ResponseEntity<String> createStripeClientSecret(@RequestBody StripePaymentDTO stripePaymentDTO) throws StripeException {
        PaymentIntent paymentIntent = stripeService.paymentIntent(stripePaymentDTO);

        return new ResponseEntity<>(paymentIntent.getClientSecret(), HttpStatus.CREATED);
    }
}
