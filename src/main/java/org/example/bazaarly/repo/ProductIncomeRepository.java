package org.example.bazaarly.repo;

import org.example.bazaarly.entity.ProductIncome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductIncomeRepository extends JpaRepository<ProductIncome, UUID> {
}