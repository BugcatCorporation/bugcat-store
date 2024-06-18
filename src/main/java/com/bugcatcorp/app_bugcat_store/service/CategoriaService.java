package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.exception.EntityNotFoundException;
import com.bugcatcorp.app_bugcat_store.model.dto.CategoriaCreacionDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.CategoriaDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Categoria;
import com.bugcatcorp.app_bugcat_store.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private CategoriaRepository repository;


    @Override
    public List<CategoriaDTO> listarCategorias() {

        var categorias = repository.findAll();

        List<CategoriaDTO> categoriasDTO = new ArrayList<>();
        // TODO: Convertir la lista de categorias a una lista de CategoriaDTO
        for (Categoria categoria : categorias) {
            CategoriaDTO categoriaDTO = new CategoriaDTO();
            categoriaDTO.setIdcategoria(categoria.getIdcategoria());
            categoriaDTO.setNombre(categoria.getNombre());
            categoriaDTO.setDescripcion(categoria.getDescripcion());
            categoriasDTO.add(categoriaDTO);
        }

        return categoriasDTO;
    }

    @Override
    public Optional<CategoriaDTO> buscarCategoriaPorId(Long id) {

        var categoria = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada con el id " + id));

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setIdcategoria(categoria.getIdcategoria());
        categoriaDTO.setNombre(categoria.getNombre());
        categoriaDTO.setDescripcion(categoria.getDescripcion());

        return Optional.of(categoriaDTO);
    }

    @Override
    public Optional<CategoriaDTO> agregarCategoria(CategoriaCreacionDTO categoriaCreacionDTO) {

        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaCreacionDTO.getNombre());
        categoria.setDescripcion(categoriaCreacionDTO.getDescripcion());
        repository.save(categoria);

        // TODO: Convertir la categoria a CategoriaDTO
        CategoriaDTO categoriaDTO = new CategoriaDTO();

        categoriaDTO.setIdcategoria(categoria.getIdcategoria());
        categoriaDTO.setNombre(categoria.getNombre());
        categoriaDTO.setDescripcion(categoria.getDescripcion());

        return Optional.of(categoriaDTO);
    }

    @Override
    public Optional<CategoriaDTO> actualizarCategoria(Long id, CategoriaCreacionDTO categoriaCreacionDTO) {

        var categoria = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada con el id " + id));
        if(categoria != null) {
            categoria.setNombre(categoriaCreacionDTO.getNombre());
            categoria.setDescripcion(categoriaCreacionDTO.getDescripcion());
            repository.save(categoria);
        }

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setIdcategoria(categoria.getIdcategoria());
        categoriaDTO.setNombre(categoria.getNombre());
        categoriaDTO.setDescripcion(categoria.getDescripcion());

        return Optional.of(categoriaDTO);
    }


}
