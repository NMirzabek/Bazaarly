package org.example.bazaarly.repo;

import org.example.bazaarly.entity.ProductOutcome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductOutcomeRepository extends JpaRepository<ProductOutcome, UUID> {
}