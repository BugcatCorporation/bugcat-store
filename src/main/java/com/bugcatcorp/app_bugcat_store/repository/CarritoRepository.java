package com.bugcatcorp.app_bugcat_store.repository;

import com.bugcatcorp.app_bugcat_store.model.entity.Carrito;
import com.bugcatcorp.app_bugcat_store.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito,Long> {
    Optional<Carrito> findByUsuario(Usuario usuario);
}
