package com.teamsoft.ms.usuario.service;

import com.teamsoft.ms.usuario.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario crearUsuario(Usuario usuario);
    List<Usuario> obtenerTodosLosUsuarios();
    Optional<Usuario> obtenerUsuarioPorId(long id);
    Optional<Usuario> getUserByEmail(String email);
    Optional<Usuario> actualizarUsuario(Long id, Usuario usuarioActualizado);
}
