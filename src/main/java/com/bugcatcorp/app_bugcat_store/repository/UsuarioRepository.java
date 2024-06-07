package com.bugcatcorp.app_bugcat_store.repository;

import com.bugcatcorp.app_bugcat_store.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByTelefono(String telefono);
}
