package com.teamsoft.ms.usuario.service;

import com.teamsoft.ms.usuario.entities.User;
import com.teamsoft.ms.usuario.model.request.CreateUserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User crearUsuario(CreateUserRequest req);
    List<User> obtenerTodosLosUsuarios();
    Optional<User> obtenerUsuarioPorId(long id);
    Optional<User> actualizarUsuario(Long id, User usuarioActualizado);
}
