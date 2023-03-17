package com.amyojiakor.Report.Application;

import com.amyojiakor.Report.Application.models.dto.CustomerOrderDTO;
import com.amyojiakor.Report.Application.models.dto.CustomerOrderResponse;
import com.amyojiakor.Report.Application.models.dto.OrderReportDTO;
import com.amyojiakor.Report.Application.models.dto.OrderReportResponse;
import com.amyojiakor.Report.Application.models.entities.Order;
import com.amyojiakor.Report.Application.models.entities.Product;
import com.amyojiakor.Report.Application.repositories.OrderRepository;
import com.amyojiakor.Report.Application.repositories.ProductRepository;
import com.amyojiakor.Report.Application.services.OrderService;
import com.amyojiakor.Report.Application.services.OrderServiceImplementation;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReportApplicationTests {

	@InjectMocks
	private OrderServiceImplementation orderServiceImpl;

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private OrderServiceImplementation orderService;

	@Test
	public void testGetOrderReportByDateRange() {
		// Setup
		LocalDate startDate = LocalDate.of(2022, 1, 1);
		LocalDate endDate = LocalDate.of(2022, 2, 1);
		Object[] orderReport1 = new Object[] {Instant.parse("2022-01-05T00:00:00Z"), 3L, 60.0};
		Object[] orderReport2 = new Object[] {Instant.parse("2022-01-10T00:00:00Z"), 2L, 40.0};
		List<Object[]> reportResults = Arrays.asList(orderReport1, orderReport2);
		when(orderRepository.getOrderReportByDateRange(startDate, endDate)).thenReturn(reportResults);

		// Execution
		OrderReportDTO orderReportDTO = new OrderReportDTO();
		orderReportDTO.setStartDate(startDate);
		orderReportDTO.setEndDate(endDate);
		List<OrderReportResponse> result = orderService.getOrderReportByDateRange(orderReportDTO);

		// Verification
		assertThat(result.size()).isEqualTo(2);
		assertThat(result.get(0).getDate()).isEqualTo(LocalDate.of(2022, 1, 5));
		assertThat(result.get(0).getTotalOrderCount()).isEqualTo(3L);
		assertThat(result.get(0).getTotalOrderAmount()).isEqualTo(60.0);
		assertThat(result.get(1).getDate()).isEqualTo(LocalDate.of(2022, 1, 10));
		assertThat(result.get(1).getTotalOrderCount()).isEqualTo(2L);
		assertThat(result.get(1).getTotalOrderAmount()).isEqualTo(40.0);
	}
}
