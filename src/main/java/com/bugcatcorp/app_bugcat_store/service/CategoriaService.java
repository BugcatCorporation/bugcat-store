package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.exception.EntityNotFoundException;
import com.bugcatcorp.app_bugcat_store.model.dto.CategoriaDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Categoria;
import com.bugcatcorp.app_bugcat_store.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CategoriaService implements ICategoriaService {

    private CategoriaRepository cr;

    @Override
    public List<Categoria> listarCategorias() {
        log.info("Listando todas las categorías");
        return cr.findAll();
    }

    @Override
    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        log.info("Buscando categoría por ID: {}", id);
        return cr.findById(id);
    }

    @Override
    public Optional<Categoria> buscarCategoriaPorNombre(String nombre) {
        log.info("Buscando categoría por nombre: {}", nombre);
        return cr.findByNombre(nombre);
    }

    @Override
    @Transactional
    public Categoria agregarCategoria(CategoriaDTO categoriaDTO) {
        log.info("Agregando nueva categoría: {}", categoriaDTO);
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());
        return cr.save(categoria);
    }

    @Override
    @Transactional
    public Categoria actualizarCategoria(Long id, CategoriaDTO categoriaDTO) {
        log.info("Actualizando categoría con ID: {} - Nuevos datos: {}", id, categoriaDTO);
        Optional<Categoria> optionalCategoria = cr.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            categoria.setNombre(categoriaDTO.getNombre());
            categoria.setDescripcion(categoriaDTO.getDescripcion());
            return cr.save(categoria);
        } else {
            throw new EntityNotFoundException("Categoria no encontrada con ID: " + id);
        }
    }

    @Override
    @Transactional
    public void borrarCategoria(Long id) {
        log.info("Borrando categoría con ID: {}", id);
        if (cr.existsById(id)) {
            cr.deleteById(id);
        } else {
            throw new EntityNotFoundException("Categoria no encontrada con ID: " + id);
        }
    }
}
