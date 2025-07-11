package org.example.bazaarly.mappers;

import org.mapstruct.Mapper;
import org.example.bazaarly.dto.request.ProductDTO;
import org.example.bazaarly.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductDTO productDTO);
}
