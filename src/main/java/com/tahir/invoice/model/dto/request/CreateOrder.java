package com.tahir.invoice.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateOrder {

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("product_id")
    private Long productId;

    private Integer quantity;
}
