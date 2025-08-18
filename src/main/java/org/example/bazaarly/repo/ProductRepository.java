package org.example.bazaarly.repo;

import org.example.bazaarly.dto.projections.ProductProjection;
import org.example.bazaarly.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<ProductProjection> findAllBy();
}