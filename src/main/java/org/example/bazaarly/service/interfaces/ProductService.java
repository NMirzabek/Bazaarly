package org.example.bazaarly.service.interfaces;

import org.example.bazaarly.dto.request.ProductDTO;
import org.example.bazaarly.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product save(ProductDTO productDTO);
}
