package com.teamsoft.ms.usuario.service;

import com.teamsoft.ms.usuario.entities.UserDetail;

import java.util.List;
import java.util.Optional;

public interface UserDetailService {
    List<UserDetail> findAll();
    Optional<UserDetail> findById(Long id);
    UserDetail save(UserDetail userDetail);
    void deleteById(Long id);
}
