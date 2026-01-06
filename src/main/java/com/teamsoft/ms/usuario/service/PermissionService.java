package com.teamsoft.ms.usuario.service;

import com.teamsoft.ms.usuario.entities.Permission;

import java.util.List;
import java.util.Optional;

public interface PermissionService {
    List<Permission> findAll();
    Optional<Permission> findById(Long id);
    Permission save(Permission permission);
    void deleteById(Long id);
}
