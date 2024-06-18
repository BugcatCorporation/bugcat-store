package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.CategoriaCreacionDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.CategoriaDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {
    List<CategoriaDTO> listarCategorias();
    Optional<CategoriaDTO> buscarCategoriaPorId(Long id);
    Optional<CategoriaDTO> agregarCategoria(CategoriaCreacionDTO categoriaCreacionDTO);
    Optional<CategoriaDTO> actualizarCategoria(Long id, CategoriaCreacionDTO categoriaCreacionDTO);
}
