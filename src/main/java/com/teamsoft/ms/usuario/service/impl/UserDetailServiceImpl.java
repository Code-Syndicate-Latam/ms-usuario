package com.teamsoft.ms.usuario.service.impl;

import com.teamsoft.ms.usuario.entities.UserDetail;
import com.teamsoft.ms.usuario.repository.UserDetailRepository;
import com.teamsoft.ms.usuario.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public List<UserDetail> findAll() {
        return userDetailRepository.findAll();
    }

    @Override
    public Optional<UserDetail> findById(Long id) {
        return userDetailRepository.findById(id);
    }

    @Override
    public UserDetail save(UserDetail userDetail) {
        return userDetailRepository.save(userDetail);
    }

    @Override
    public void deleteById(Long id) {
        userDetailRepository.deleteById(id);
    }
}
