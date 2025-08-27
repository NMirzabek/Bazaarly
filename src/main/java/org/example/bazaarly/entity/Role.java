package org.example.bazaarly.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bazaarly.entity.base.BaseEntity;
import org.springframework.security.core.GrantedAuthority;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {

    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
