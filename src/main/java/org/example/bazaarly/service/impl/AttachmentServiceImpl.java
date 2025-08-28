package org.example.bazaarly.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.bazaarly.entity.Attachment;
import org.example.bazaarly.repo.AttachmentRepository;
import org.example.bazaarly.service.interfaces.AttachmentService;
import org.example.bazaarly.service.interfaces.S3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final S3Service s3Service;
    private final AttachmentRepository attachmentRepository;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.region}")
    private String region;

    @Override
    public UUID upload(MultipartFile file) {
        String key = s3Service.uploadImage(file);
        String url = "https://" + bucketName + ".s3." + region + "." + "amazonaws.com/" + key;
        Attachment attachment = new Attachment();
        attachment.setImgUrl(url);
        Attachment saved = attachmentRepository.save(attachment);
        return saved.getId();
    }

    @SneakyThrows
    @Override
    public void get(UUID attachmentId, HttpServletResponse response) {
        Attachment attachment = attachmentRepository.findById(attachmentId).orElseThrow();
        byte[] image = s3Service.getImage(attachment.getImgUrl());
        response.getOutputStream().write(image);
    }
}
