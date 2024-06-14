package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.entity.Rol;
import com.bugcatcorp.app_bugcat_store.model.entity.Usuario;
import com.bugcatcorp.app_bugcat_store.repository.RolRepository;
import com.bugcatcorp.app_bugcat_store.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario buscarUsuarioXNomUsuario(String nomusuario) {
        return usuarioRepository.findByNombre(nomusuario);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {

        Optional<Rol> optionalRolUser = rolRepository.findByRol("ROLE_USER");
        Set<Rol> roles = new HashSet<>();

        optionalRolUser.ifPresent(roles::add);

        if(usuario.isAdmin()){
            Optional<Rol> optionalRolAdmin = rolRepository.findByRol("ROLE_ADMIN");
            optionalRolAdmin.ifPresent(roles::add);
        }

        usuario.setRoles(roles);
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuario.setActivo(true);

        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarUsuarioXIdUsuario(Long idusuario) {
        return usuarioRepository.findById(idusuario).orElse(null);
    }
}
