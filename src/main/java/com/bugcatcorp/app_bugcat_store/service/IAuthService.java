package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.UsuarioDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.UsuarioUpdateDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IAuthService {
    List<Usuario> ListarUsuarios();
    Optional<Usuario> buscarUsuarioPorId(Long idUsuario);
    Optional<Usuario> buscarUsuarioxNombre(String nombre);
    Optional<Usuario> buscarUsuarioxEmail(String email);
    Usuario agregarUsuario(UsuarioDTO usuarioDTO);
    Usuario actualizarUsuario(Long idUsuario, UsuarioUpdateDTO usuarioUpdateDTO);
    void borrarUsuario(Long id);
}

