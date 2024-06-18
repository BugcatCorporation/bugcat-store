package com.bugcatcorp.app_bugcat_store.controller;

import com.bugcatcorp.app_bugcat_store.model.dto.CategoriaCreacionDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.CategoriaDTO;
import com.bugcatcorp.app_bugcat_store.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<CategoriaDTO> categoriasDTO = categoriaService.listarCategorias();
        return new ResponseEntity<>(categoriasDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        CategoriaDTO categoriaDTO = categoriaService.buscarCategoriaPorId(id).orElse(null);
        if(categoriaDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(categoriaDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoriaCreacionDTO categoriaCreacionDTO){

        Optional<CategoriaDTO> categoriaDTO = categoriaService.agregarCategoria(categoriaCreacionDTO);
        if(categoriaDTO.isPresent()) {
            return new ResponseEntity<>(categoriaDTO.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CategoriaCreacionDTO categoriaCreacionDTO){
        Optional<CategoriaDTO> categoriaDTO = categoriaService.actualizarCategoria(id, categoriaCreacionDTO);
        if(categoriaDTO.isPresent()) {
            return new ResponseEntity<>(categoriaDTO.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
