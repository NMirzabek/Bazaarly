package org.example.bazaarly.controller;

import lombok.RequiredArgsConstructor;
import org.example.bazaarly.dto.request.CategoryDTO;
import org.example.bazaarly.entity.Category;
import org.example.bazaarly.service.interfaces.CategoryService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.bazaarly.utils.AppConstants.*;

@RestController
@RequestMapping(API_PATH + API_VERSION + "/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public HttpEntity<?> getAll() {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public HttpEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category saved = categoryService.save(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);

    }
}
