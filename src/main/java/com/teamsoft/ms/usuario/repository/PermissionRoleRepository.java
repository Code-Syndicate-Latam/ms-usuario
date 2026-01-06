package com.teamsoft.ms.usuario.repository;

import com.teamsoft.ms.usuario.entities.PermissionRole;
import com.teamsoft.ms.usuario.entities.keys.PermissionRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRoleRepository extends JpaRepository<PermissionRole, PermissionRoleId> {
}
