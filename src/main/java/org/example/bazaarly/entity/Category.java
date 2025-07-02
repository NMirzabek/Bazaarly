package org.example.bazaarly.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bazaarly.entity.base.BaseEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Category extends BaseEntity {
    private String name;
}
