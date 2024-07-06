package com.bugcatcorp.app_bugcat_store.controller;

import com.bugcatcorp.app_bugcat_store.model.dto.UsuarioDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.UsuarioUpdateDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Usuario;
import com.bugcatcorp.app_bugcat_store.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<List<UsuarioDTO>> obtenerTodosLosUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.listarTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<UsuarioDTO> usuarioDTO = usuarioService.buscarUsuarioPorId(id);
        if (usuarioDTO.isPresent()) {
            return ResponseEntity.ok(usuarioDTO.get());
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Usuario user, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardarUsuario(user));
    }



    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Usuario user, BindingResult result) {
        user.setAdmin(true);
        return create(user, result);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
        Optional<UsuarioDTO> usuarioDTO = usuarioService.actualizarUsuario(id, usuarioUpdateDTO);
        if (usuarioDTO.isPresent()) {
            return ResponseEntity.ok(usuarioDTO.get());
        }
        return ResponseEntity.badRequest().build();
    }

}
