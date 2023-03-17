package com.amyojiakor.Report.Application.controller;

import com.amyojiakor.Report.Application.models.dto.OrderReportDTO;
import com.amyojiakor.Report.Application.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/report/")
public class ReportController {
    @Autowired
    OrderService orderService;
    @PostMapping("get-by-range")
    public ResponseEntity<?> createProduct(@RequestBody OrderReportDTO orderReportDTO){

        return ResponseEntity.ok(orderService.getOrderReportByDateRange(orderReportDTO));
    }
}
