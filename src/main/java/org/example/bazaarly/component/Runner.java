package org.example.bazaarly.component;

import lombok.RequiredArgsConstructor;
import org.example.bazaarly.entity.Role;
import org.example.bazaarly.repo.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.saveAll(new ArrayList<>(List.of(
                    new Role("ROLE_ADMIN"),
                    new Role("ROLE_USER")
            )));
        }
    }
}
