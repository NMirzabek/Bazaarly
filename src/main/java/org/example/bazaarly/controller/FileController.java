package org.example.bazaarly.controller;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletResponse;
import org.example.bazaarly.entity.Attachment;
import org.example.bazaarly.repo.AttachmentRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

import static org.example.bazaarly.utils.AppConstants.*;

@MultipartConfig
@RestController
@RequestMapping(API_PATH + API_VERSION + "/file")
public class FileController {

    private final AttachmentRepository attachmentRepository;

    public FileController(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @PostMapping
    public UUID uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        Attachment attachment = new Attachment();
        attachment.setImgUrl(file.getOriginalFilename());
        attachment.setContent(file.getBytes());
        Attachment saved = attachmentRepository.save(attachment);
        return saved.getId();
    }

    @GetMapping("/{attachmentId}")
    public void getFile(@PathVariable UUID attachmentId, HttpServletResponse response) throws IOException {
        Attachment attachment = attachmentRepository.findById(attachmentId).orElseThrow();
        response.getOutputStream().write(attachment.getContent());
    }
}
