package org.example.bazaarly.dto.request;

import lombok.Value;

@Value
public class LoginDTO {
    String email;
    String password;
}
