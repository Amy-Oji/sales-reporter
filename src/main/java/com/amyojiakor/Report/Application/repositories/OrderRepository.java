package com.amyojiakor.Report.Application.repositories;

import com.amyojiakor.Report.Application.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT DATE_TRUNC('day', o.order_date) as date, COUNT(DISTINCT o.id) as total_order, SUM(p.price_per_unit * p.quantity_ordered) as total_order_amount FROM orders o JOIN products p ON o.id = p.order_id WHERE o.order_date BETWEEN :startDate AND :endDate GROUP BY date", nativeQuery = true)
    List<Object[]> getOrderReportByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
