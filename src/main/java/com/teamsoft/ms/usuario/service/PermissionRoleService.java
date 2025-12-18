package com.teamsoft.ms.usuario.service;

import com.teamsoft.ms.usuario.entities.PermissionRole;
import com.teamsoft.ms.usuario.entities.keys.PermissionRoleId;

import java.util.List;
import java.util.Optional;

public interface PermissionRoleService {
    List<PermissionRole> findAll();
    Optional<PermissionRole> findById(PermissionRoleId id);
    PermissionRole save(PermissionRole permissionRole);
    void deleteById(PermissionRoleId id);
    PermissionRole assignPermissionToRole(Long roleId, Long permissionId);
    void revokePermissionFromRole(Long roleId, Long permissionId);
}
