package com.ecommerce.app.dto;

import lombok.Data;

@Data
public class StripePaymentDTO {
    private Long amount;
    private String currency;
}
