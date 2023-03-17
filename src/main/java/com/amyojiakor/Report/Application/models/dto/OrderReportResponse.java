package com.amyojiakor.Report.Application.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
public class OrderReportResponse {
    private LocalDate date;
    private Long totalOrderCount;
    private Double totalOrderAmount;

}
