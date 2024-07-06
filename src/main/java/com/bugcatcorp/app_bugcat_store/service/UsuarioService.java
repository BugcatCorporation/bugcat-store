package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.exception.EntityNotFoundException;
import com.bugcatcorp.app_bugcat_store.model.dto.UsuarioDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.UsuarioDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.UsuarioUpdateDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Usuario;
import com.bugcatcorp.app_bugcat_store.model.entity.Rol;
import com.bugcatcorp.app_bugcat_store.model.entity.Usuario;
import com.bugcatcorp.app_bugcat_store.repository.RolRepository;
import com.bugcatcorp.app_bugcat_store.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public Optional<UsuarioDTO> buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con el id " + id));

        return Optional.of(convertirAUsuarioDTO(usuario));
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

    public List<UsuarioDTO>listarTodosLosUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        for (Usuario usuario: usuarios){
            usuariosDTO.add(convertirAUsuarioDTO(usuario));
        }
        return usuariosDTO;
    }
    
    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarUsuarioXIdUsuario(Long idusuario) {
        return usuarioRepository.findById(idusuario).orElse(null);
    }

    @Override
    public Optional<UsuarioDTO> actualizarUsuario(Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con el id " + id));

        if (usuarioUpdateDTO.getNombre() != null) {
            usuario.setNombre(usuarioUpdateDTO.getNombre());
        }
        if (usuarioUpdateDTO.getEmail() != null) {
            usuario.setEmail(usuarioUpdateDTO.getEmail());
        }
        if (usuarioUpdateDTO.getDireccion() != null) {
            usuario.setDireccion(usuarioUpdateDTO.getDireccion());
        }
        if (usuarioUpdateDTO.getTelefono() != null) {
            usuario.setTelefono(usuarioUpdateDTO.getTelefono());
        }
        if (usuarioUpdateDTO.getContrasena() != null) {
            usuario.setContrasena(usuarioUpdateDTO.getContrasena());
        }

        Usuario usuarioActualizado = usuarioRepository.save(usuario);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdusuario(usuarioActualizado.getIdusuario());
        usuarioDTO.setNombre(usuarioActualizado.getNombre());
        usuarioDTO.setEmail(usuarioActualizado.getEmail());
        usuarioDTO.setDireccion(usuarioActualizado.getDireccion());
        usuarioDTO.setTelefono(usuarioActualizado.getTelefono());
        usuarioDTO.setActivo(usuarioActualizado.getActivo());

        return Optional.of(usuarioDTO);
    }


    UsuarioDTO convertirAUsuarioDTO(Usuario Usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdusuario(Usuario.getIdusuario());
        dto.setNombre(Usuario.getNombre());
        dto.setEmail(Usuario.getEmail());
        dto.setContrasena(Usuario.getContrasena());
        dto.setDireccion(Usuario.getDireccion());
        dto.setTelefono(Usuario.getTelefono());
        dto.setActivo(Usuario.getActivo());
        return dto;
    }
}
