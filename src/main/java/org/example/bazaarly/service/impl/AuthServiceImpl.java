package org.example.bazaarly.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.example.bazaarly.dto.request.LoginDTO;
import org.example.bazaarly.dto.request.RegisterDTO;
import org.example.bazaarly.dto.response.LoginRes;
import org.example.bazaarly.entity.Role;
import org.example.bazaarly.entity.User;
import org.example.bazaarly.repo.RoleRepository;
import org.example.bazaarly.repo.UserRepository;
import org.example.bazaarly.security.JwtService;
import org.example.bazaarly.service.interfaces.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginRes login(LoginDTO loginDTO) {
        var auth = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        authenticationManager.authenticate(auth);
        String token = jwtService.generateToken(loginDTO.getEmail());
        return new LoginRes(token);
    }

    @SneakyThrows
    @Override
    public void register(RegisterDTO registerDTO) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new BadRequestException("Password do not match");
        }
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        List<Role> roleUser = roleRepository.findAllByNameIn(List.of("ROLE_USER"));
        user.setRoles(roleUser);
        userRepository.save(user);
    }
}
