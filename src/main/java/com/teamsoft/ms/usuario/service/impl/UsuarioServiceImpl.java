package com.teamsoft.ms.usuario.service.impl;

import com.teamsoft.ms.usuario.entities.Usuario;
import com.teamsoft.ms.usuario.repository.UsuarioRepository;
import com.teamsoft.ms.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        // Encriptar la contrasena antes de guardarla con encoder
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Optional<Usuario> actualizarUsuario(Long id, Usuario usuarioActualizado) {
        //Buscar el usuario existente por ID
        Optional<Usuario> usuarioExistenteOpt = usuarioRepository.findById(id);

        if (usuarioExistenteOpt.isEmpty()) {
            //Si no se encuentra el usuario retorna un Optional vacio
            return Optional.empty();
        }
        //Obtener la instancia del usuario de la base de datos
        Usuario usuarioExistente = usuarioExistenteOpt.get();
        //Actualizar los campos del usuario existente con los nuevos datos
        usuarioExistente.setRole(usuarioActualizado.getRole());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        usuarioExistente.setCreationDate(usuarioActualizado.getCreationDate());
        usuarioExistente.setUpdateDate(usuarioActualizado.getUpdateDate());
        usuarioExistente.setStatus(usuarioActualizado.getStatus());
        // Manejo especial para la contrasena solo se actualiza si se proporciona una nueva
        if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
            usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
        }
        //Guardar el usuario actualizado en la base de datos
        Usuario usuarioGuardado = usuarioRepository.save(usuarioExistente);
        //Retornar el usuario guardado envuelto en un Optional
        return Optional.of(usuarioGuardado);
    }
}
