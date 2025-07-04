package org.example.bazaarly.service.interfaces;

import org.example.bazaarly.dto.request.LoginDTO;
import org.example.bazaarly.dto.request.RegisterDTO;
import org.example.bazaarly.dto.response.LoginRes;

public interface AuthService {

    LoginRes login(LoginDTO loginDTO);


    void register(RegisterDTO registerDTO);
}
