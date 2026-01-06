package com.teamsoft.ms.usuario.service.impl;

import com.teamsoft.ms.usuario.entities.DocumentType;
import com.teamsoft.ms.usuario.entities.User;
import com.teamsoft.ms.usuario.model.request.CreateUserRequest;
import com.teamsoft.ms.usuario.repository.DocumentTypeRepository;
import com.teamsoft.ms.usuario.repository.UserRepository;
import com.teamsoft.ms.usuario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;


    @Override
    public User crearUsuario(CreateUserRequest req) {
        DocumentType dt = documentTypeRepository.findById(req.getDocumentTypeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "document_type_id no existe"));

        User u = new User();
        u.setDocumentType(dt);
        u.setIdentificationNumber(req.getIdentificationNumber());
        u.setDateOfBirth(req.getDateOfBirth());
        u.setFirstName(req.getFirstName());
        u.setMiddleName(req.getMiddleName());
        u.setFirstLastName(req.getFirstLastName());
        u.setSecondLastName(req.getSecondLastName());
        u.setAddress(req.getAddress());
        u.setPhone(req.getPhone());


        //usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(u);
    }

    @Override
    public List<User> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<User> obtenerUsuarioPorId(long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<User> actualizarUsuario(Long id, User usuarioActualizado) {
        //Buscar el usuario existente por ID
        Optional<User> usuarioExistenteOpt = usuarioRepository.findById(id);

        if (usuarioExistenteOpt.isEmpty()) {
            //Si no se encuentra el usuario retorna un Optional vacio
            return Optional.empty();
        }
        //Obtener la instancia del usuario de la base de datos
        User usuarioExistente = usuarioExistenteOpt.get();
        //Actualizar los campos del usuario existente con los nuevos datos
        usuarioExistente.setDocumentType(usuarioActualizado.getDocumentType());
        usuarioExistente.setIdentificationNumber(usuarioActualizado.getIdentificationNumber());
        usuarioExistente.setDateOfBirth(usuarioActualizado.getDateOfBirth());
        usuarioExistente.setFirstName(usuarioActualizado.getFirstName());
        usuarioExistente.setMiddleName(usuarioActualizado.getMiddleName());
        usuarioExistente.setFirstLastName(usuarioActualizado.getFirstLastName());
        usuarioExistente.setSecondLastName(usuarioActualizado.getSecondLastName());
        usuarioExistente.setAddress(usuarioActualizado.getAddress());
        usuarioExistente.setCreationDate(usuarioActualizado.getCreationDate());
        usuarioExistente.setUpdateDate(usuarioActualizado.getUpdateDate());
        usuarioExistente.setStatus(usuarioActualizado.getStatus());
        usuarioExistente.setPhone(usuarioActualizado.getPhone());
        //Guardar el usuario actualizado en la base de datos
        User usuarioGuardado = usuarioRepository.save(usuarioExistente);
        //Retornar el usuario guardado envuelto en un Optional
        return Optional.of(usuarioGuardado);
    }
}
