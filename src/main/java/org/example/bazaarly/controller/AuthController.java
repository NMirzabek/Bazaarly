package org.example.bazaarly.controller;

import lombok.RequiredArgsConstructor;
import org.example.bazaarly.dto.request.LoginDTO;
import org.example.bazaarly.dto.request.RegisterDTO;
import org.example.bazaarly.dto.response.LoginRes;
import org.example.bazaarly.service.interfaces.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.bazaarly.utils.AppConstants.*;

@RestController
@RequestMapping(API_PATH + API_VERSION +"/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public HttpEntity<?> login(@RequestBody LoginDTO loginDTO) {
        LoginRes loginRes = authService.login(loginDTO);
        return ResponseEntity.ok(loginRes);
    }

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        authService.register(registerDTO);
        return ResponseEntity.ok().build();
    }
}
