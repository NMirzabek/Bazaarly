package org.example.bazaarly.repo;

import org.example.bazaarly.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    List<Role> findAllByNameIn(List<String> names);
}