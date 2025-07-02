package org.example.bazaarly.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bazaarly.entity.base.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductIncome extends BaseEntity {

    private Integer amount;
    private LocalDateTime dateTime;

    @ManyToOne
    private Product product;
}
