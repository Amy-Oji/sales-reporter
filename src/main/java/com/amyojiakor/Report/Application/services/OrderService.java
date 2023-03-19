package com.amyojiakor.Report.Application.services;

import com.amyojiakor.Report.Application.models.dto.OrderReportDTO;
import com.amyojiakor.Report.Application.models.dto.OrderReportResponse;

import java.util.List;

public interface OrderService {

    public List<OrderReportResponse> getOrderReportByDateRange(OrderReportDTO orderReportDTO);
}
