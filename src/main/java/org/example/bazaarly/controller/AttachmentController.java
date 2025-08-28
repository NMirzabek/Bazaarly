package org.example.bazaarly.controller;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.bazaarly.entity.Attachment;
import org.example.bazaarly.repo.AttachmentRepository;
import org.example.bazaarly.service.interfaces.AttachmentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

import static org.example.bazaarly.utils.AppConstants.*;

@MultipartConfig
@RestController
@RequestMapping(API_PATH + API_VERSION + "/file")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping
    public UUID uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return attachmentService.upload(file);
    }

    @GetMapping("/{attachmentId}")
    public void getFile(@PathVariable UUID attachmentId, HttpServletResponse response) throws IOException {
        attachmentService.get(attachmentId, response);
    }
}
