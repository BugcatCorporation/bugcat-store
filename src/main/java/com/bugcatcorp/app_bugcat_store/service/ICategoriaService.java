package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.CategoriaDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {
    List<Categoria> listarCategorias();
    Optional<Categoria> buscarCategoriaPorId(Long id);
    Optional<Categoria> buscarCategoriaPorNombre(String nombre);
    Categoria agregarCategoria(CategoriaDTO categoriaDTO);
    Categoria actualizarCategoria(Long id, CategoriaDTO categoriaDTO);
    void borrarCategoria(Long id);
}
