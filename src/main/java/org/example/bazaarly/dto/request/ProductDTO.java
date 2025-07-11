package org.example.bazaarly.dto.request;

import lombok.Value;

import java.util.UUID;

@Value
public class ProductDTO {

    String name;
    Double price;
    UUID categoryId;
    UUID attachmentId;
}
