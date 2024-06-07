package com.bugcatcorp.app_bugcat_store.repository;

import com.bugcatcorp.app_bugcat_store.model.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {
    Optional<Rol> findByRol(String rol);
}
