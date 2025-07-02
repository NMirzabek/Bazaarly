package org.example.bazaarly.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bazaarly.entity.base.BaseEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product extends BaseEntity {
    private String name;
    private Double price;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Attachment attachment;
}
