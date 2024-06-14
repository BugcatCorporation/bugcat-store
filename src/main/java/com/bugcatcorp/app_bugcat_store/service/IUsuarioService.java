package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario buscarUsuarioXNomUsuario(String nomusuario);
    Usuario guardarUsuario(Usuario usuario);
    List<Usuario> listarUsuario();
    Usuario buscarUsuarioXIdUsuario(Long idusuario);
}
