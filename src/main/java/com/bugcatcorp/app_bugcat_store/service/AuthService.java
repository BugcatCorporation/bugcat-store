package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.exception.EntityNotFoundException;
import com.bugcatcorp.app_bugcat_store.model.dto.UsuarioDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.UsuarioUpdateDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Rol;
import com.bugcatcorp.app_bugcat_store.model.entity.Usuario;
import com.bugcatcorp.app_bugcat_store.repository.RolRepository;
import com.bugcatcorp.app_bugcat_store.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class AuthService implements IAuthService {

    private final UsuarioRepository ur;
    private final RolRepository rr;

    @Override
    public List<Usuario> ListarUsuarios() {
        log.info("Listando todos los usuarios");
        List<Usuario> usuarios = ur.findAll();
        log.info("Usuarios encontrados: {}", usuarios.size());
        return usuarios;
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorId(Long idUsuario) {
        log.info("Buscando usuario por ID: {}", idUsuario);
        Optional<Usuario> usuario = ur.findById(idUsuario);
        if (usuario.isPresent()) {
            log.info("Usuario encontrado: {}", usuario.get());
        } else {
            log.warn("Usuario con ID: {} no encontrado", idUsuario);
        }
        return usuario;
    }

    @Override
    public Optional<Usuario> buscarUsuarioxNombre(String nombre){
        log.info("Buscando usuario por ID: {}", nombre);
        Optional<Usuario> usuario = ur.findByNombre(nombre);
        if (usuario.isPresent()) {
            log.info("Usuario encontrado: {}", usuario.get());
        } else {
            log.warn("Usuario con ID: {} no encontrado", nombre);
        }
        return usuario;
    }

    @Override
    public Optional<Usuario> buscarUsuarioxEmail(String email) {
        log.info("Buscando usuario por email: {}", email);
        Optional<Usuario> usuario = ur.findByEmail(email);
        if (usuario.isPresent()) {
            log.info("Usuario encontrado: {}", usuario);
        } else {
            log.warn("Usuario con email: {} no encontrado", email);
        }
        return usuario;
    }

    @Override
    @Transactional
    public Usuario agregarUsuario(UsuarioDTO usuarioDTO) {
        log.info("Agregando nuevo usuario con email: {}", usuarioDTO.getEmail());
        if (ur.findByEmail(usuarioDTO.getEmail()) != null) {
            log.warn("El correo {} ya está registrado", usuarioDTO.getEmail());
            throw new EntityNotFoundException("El correo ya está registrado");
        }
        if (ur.findByTelefono(usuarioDTO.getTelefono()) != null) {
            log.warn("El teléfono {} ya está registrado", usuarioDTO.getTelefono());
            throw new EntityNotFoundException("El teléfono ya está registrado");
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setContrasena(usuarioDTO.getContrasena());
        usuario.setDireccion(usuarioDTO.getDireccion());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setActivo(usuarioDTO.getActivo());

        Optional<Rol> rolClienteOptional = rr.findByRol("Cliente");
        if (rolClienteOptional.isEmpty()) {
            throw new EntityNotFoundException("Rol de cliente no encontrado en la base de datos");
        }
        Rol rolCliente = rolClienteOptional.get();
        usuario.setRoles(Set.of(rolCliente));
        Usuario nuevoUsuario = ur.save(usuario);
        log.info("Usuario creado con éxito: {}", nuevoUsuario);
        return nuevoUsuario;
    }


    @Override
    @Transactional
    public Usuario actualizarUsuario(Long idUsuario, UsuarioUpdateDTO usuarioUpdateDTO) {
        log.info("Actualizando usuario con ID: {}", idUsuario);
        Usuario usuario = ur.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        if (usuarioUpdateDTO.getDireccion() != null) {
            usuario.setDireccion(usuarioUpdateDTO.getDireccion());
        }
        if (usuarioUpdateDTO.getTelefono() != null) {
            Optional<Usuario> usuarioConMismoTelefono = ur.findByTelefono(usuarioUpdateDTO.getTelefono());
            if (usuarioConMismoTelefono.isPresent() && !usuarioConMismoTelefono.get().getIdusuario().equals(idUsuario)) {
                log.warn("El teléfono {} ya está registrado", usuarioUpdateDTO.getTelefono());
                throw new EntityNotFoundException("El teléfono ya está registrado");
            }
            usuario.setTelefono(usuarioUpdateDTO.getTelefono());
        }
        if (usuarioUpdateDTO.getContrasena() != null) {
            usuario.setContrasena(usuarioUpdateDTO.getContrasena());
        }
        Usuario usuarioActualizado = ur.save(usuario);
        log.info("Usuario actualizado con éxito: {}", usuarioActualizado);
        return usuarioActualizado;
    }

    public void borrarUsuario(Long id) {
        log.info("Borrando usuario con ID: {}", id);
        if (ur.existsById(id)) {
            ur.deleteById(id);
            log.info("Usuario con ID: {} eliminado con éxito", id);
        } else {
            log.warn("Usuario con ID: {} no existe", id);
            throw new EntityNotFoundException("El usuario no existe");
        }
    }
}
