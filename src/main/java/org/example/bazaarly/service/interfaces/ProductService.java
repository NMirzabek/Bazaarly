package org.example.bazaarly.service.interfaces;

import org.example.bazaarly.dto.projections.ProductProjection;
import org.example.bazaarly.dto.request.ProductDTO;
import org.example.bazaarly.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductProjection> getAll();

    Product save(ProductDTO productDTO);
}
