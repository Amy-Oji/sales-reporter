package com.amyojiakor.Report.Application.services;

import com.amyojiakor.Report.Application.models.dto.CustomerOrderResponse;
import com.amyojiakor.Report.Application.models.dto.OrderReportDTO;
import com.amyojiakor.Report.Application.models.dto.OrderReportResponse;
import com.amyojiakor.Report.Application.models.entities.Order;
import com.amyojiakor.Report.Application.models.entities.Product;
import com.amyojiakor.Report.Application.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class OrderServiceImplementation implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderReportResponse> getOrderReportByDateRange(OrderReportDTO orderReportDTO) {

        List<Object[]> reportResults = orderRepository
                .getOrderReportByDateRange(orderReportDTO.getStartDate(), orderReportDTO.getEndDate());

        List<OrderReportResponse> orderReportDto = new ArrayList<>();

        for (Object[] result : reportResults) {
            Instant instant = (Instant) result[0];
            LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            Long totalOrderCount = (Long) result[1];
            Double totalOrderAmount = (Double) result[2];
            OrderReportResponse OrderReportResponse = new OrderReportResponse(date, totalOrderCount, totalOrderAmount);
            orderReportDto.add(OrderReportResponse);
        }
        return orderReportDto;
    }

    @Transactional
    @KafkaListener(topics = "${kafka.topic.order}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CustomerOrderResponse customerOrderResponse) {
        Order order = new Order();
        order.setCustomerName(customerOrderResponse.getCustomerOrderDTO().getCustomerName());
        order.setCustomerNum(customerOrderResponse.getCustomerOrderDTO().getCustomerPhoneNum());

        var orderProducts = customerOrderResponse.getCustomerOrderDTO().getProducts();
        var productDetails = customerOrderResponse.getProductDetails();
        List<Product> productList = new ArrayList<>();

        for (Map.Entry<Long, Integer> orderProduct : orderProducts.entrySet()) {
            Long productId = orderProduct.getKey();
            Integer quantity = orderProduct.getValue();

            for (Map.Entry<Long, Double> productDetail : productDetails.entrySet()) {
                Product product = new Product();
                if (Objects.equals(productId, productDetail.getKey()))
                    product.setQuantityOrdered(quantity);
                    product.setIDFromInventoryApp(orderProduct.getKey());
                    product.setPricePerUnit(productDetail.getValue());
                    product.setOrder(order);
                    productList.add(product);
            }

            order.setOrder_date(LocalDate.from(customerOrderResponse.getOrder_date()));
            order.setProducts(productList);
            order.setSum(customerOrderResponse.getSum());

            orderRepository.save(order);
        }
    }

}