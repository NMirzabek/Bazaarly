package org.example.bazaarly.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.example.bazaarly.dto.projections.ProductProjection;
import org.example.bazaarly.dto.request.ProductDTO;
import org.example.bazaarly.entity.Product;
import org.example.bazaarly.service.interfaces.ProductService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.bazaarly.utils.AppConstants.*;

@RestController
@RequestMapping(API_PATH + API_VERSION + "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public HttpEntity<?> getProducts(
            @Parameter(name = "categoryName", description = "Choose one category")
            @RequestParam(required = false) String categoryName
    ) {
        List<ProductProjection> products = productService.getAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        Product saved = productService.save(productDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
