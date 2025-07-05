package org.example.bazaarly.service.interfaces;

import org.example.bazaarly.dto.CategoryDTO;
import org.example.bazaarly.entity.Category;

import java.util.List;

public interface CategoryService {
    Category save(CategoryDTO categoryDTO);

    List<Category> getAll();
}
