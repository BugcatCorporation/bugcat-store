package com.bugcatcorp.app_bugcat_store.security;

import com.bugcatcorp.app_bugcat_store.model.entity.Usuario;
import com.bugcatcorp.app_bugcat_store.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetalleUsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);
        if(usuarioOptional.isEmpty()){
            throw new UsernameNotFoundException(String.format("Usuario %s no existe en el sistema", username));
        }
        Usuario user = usuarioOptional.orElseThrow();

        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRol()))
                .collect(Collectors.toList());

        return new User(user.getUsername(), user.getContrasena(), user.getActivo(), true, true, true, authorities);
    }

}
