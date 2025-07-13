package org.example.bazaarly.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.bazaarly.dto.request.ProductDTO;
import org.example.bazaarly.entity.Attachment;
import org.example.bazaarly.entity.Category;
import org.example.bazaarly.entity.Product;
import org.example.bazaarly.mappers.ProductMapper;
import org.example.bazaarly.repo.AttachmentRepository;
import org.example.bazaarly.repo.CategoryRepository;
import org.example.bazaarly.repo.ProductRepository;
import org.example.bazaarly.service.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final AttachmentRepository attachmentRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        if (productDTO.getAttachmentId() != null) {
            Attachment attachment = attachmentRepository.findById(productDTO.getAttachmentId()).orElseThrow();
            product.setAttachment(attachment);
        }

        if (productDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow();
            product.setCategory(category);
        }
        return productRepository.save(product);
    }
}
