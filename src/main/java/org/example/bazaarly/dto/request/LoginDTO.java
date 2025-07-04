package org.example.bazaarly.dto.request;

import lombok.Value;

@Value
public class LoginDTO {
    private String email;
    private String password;
}
