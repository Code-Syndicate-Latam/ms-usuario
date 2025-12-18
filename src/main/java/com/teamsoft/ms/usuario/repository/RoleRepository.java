package com.teamsoft.ms.usuario.repository;

import com.teamsoft.ms.usuario.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
