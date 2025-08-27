package org.example.bazaarly.dto.projections;

import java.util.UUID;

public interface ProductProjection {

    UUID getId();
    String getName();
    Double getPrice();
    String getCategoryName();
    UUID getAttachmentId();
}
