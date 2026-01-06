package com.teamsoft.ms.usuario.controller;

import com.teamsoft.ms.usuario.entities.User;
import com.teamsoft.ms.usuario.model.request.CreateUserRequest;
import com.teamsoft.ms.usuario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService usuarioService;

    //Distintas maneras de inyectar dependencias, Field Injection - Setter Injection - Constructor Injection.
    @Autowired
    public UserController(final UserService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<User> crearUsuario(@RequestBody CreateUserRequest req) {
        User nuevoUsuario = usuarioService.crearUsuario(req);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> actualizarUsuario(@PathVariable Long id, @RequestBody User usuario) {
        Optional<User> usuarioActualizadoOpt = usuarioService.actualizarUsuario(id, usuario);

        // Si el servicio actualizo el usuario, retorna 200 OK con el usuario actualizado
        // Si el servicio no encontro el usuario, retorna 404 Not Found
        return usuarioActualizadoOpt.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<User>> obtenerTodosLosUsuarios() {
        List<User> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> obtenerUsuarioPorId(@PathVariable long id) {
        return usuarioService.obtenerUsuarioPorId(id)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
