package com.amyojiakor.Report.Application.models.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class CustomerOrderResponse {
    private CustomerOrderDTO customerOrderDTO;
    private Map<Long, Double> productDetails;
    private double sum;
    private LocalDateTime order_date;
}
