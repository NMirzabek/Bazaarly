package org.example.bazaarly.service.impl;

import org.example.bazaarly.entity.Product;
import org.example.bazaarly.service.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> getAll() {
        return List.of();
    }
}
