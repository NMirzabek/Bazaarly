package org.example.bazaarly.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    String uploadImage(MultipartFile image);
    byte[] getImage(String url);
}
