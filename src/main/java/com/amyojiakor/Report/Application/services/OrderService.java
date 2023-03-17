package com.amyojiakor.Report.Application.services;

import com.amyojiakor.Report.Application.models.dto.CustomerOrderResponse;
import com.amyojiakor.Report.Application.models.dto.OrderReportDTO;
import com.amyojiakor.Report.Application.models.dto.OrderReportResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {

    public List<OrderReportResponse> getOrderReportByDateRange(OrderReportDTO orderReportDTO);
}
