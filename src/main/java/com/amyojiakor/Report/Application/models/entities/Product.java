package com.amyojiakor.Report.Application.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long IDFromInventoryApp;


    private int quantityOrdered;

    private double pricePerUnit;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties("products")
    private Order order;


}
