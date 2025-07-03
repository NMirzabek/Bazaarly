package org.example.bazaarly.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.bazaarly.dto.CategoryDTO;
import org.example.bazaarly.entity.Category;
import org.example.bazaarly.repo.CategoryRepository;
import org.example.bazaarly.service.interfaces.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category save(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO.getName());
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
