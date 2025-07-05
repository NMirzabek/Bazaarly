package org.example.bazaarly.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    String email;
    String password;
    String confirmPassword;
    String firstName;
    String lastName;

}
