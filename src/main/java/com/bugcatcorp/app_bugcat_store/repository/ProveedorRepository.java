package com.bugcatcorp.app_bugcat_store.repository;

import com.bugcatcorp.app_bugcat_store.model.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {
    Optional<Proveedor>findByNombre(String nombre);
    Optional<Proveedor>findByEmail(String email);
    Optional<Proveedor>findByCelular(String celular);
}
