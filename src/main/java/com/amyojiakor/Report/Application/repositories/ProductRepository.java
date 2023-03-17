package com.amyojiakor.Report.Application.repositories;

import com.amyojiakor.Report.Application.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
