package com.bugcatcorp.app_bugcat_store.repository;

import com.bugcatcorp.app_bugcat_store.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    Optional<Categoria> findByNombre(String nombre);
}
