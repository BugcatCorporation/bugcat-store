package com.bugcatcorp.app_bugcat_store.controller;

import com.bugcatcorp.app_bugcat_store.model.dto.ResenaCreacionDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.ResenaDTO;
import com.bugcatcorp.app_bugcat_store.service.IResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resena")
public class ResenaController {

    @Autowired
    private IResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<ResenaDTO>> listarResenas() {
        List<ResenaDTO> resenas = resenaService.listarResenas();
        return ResponseEntity.ok(resenas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenaDTO> obtenerResenaPorId(@PathVariable Long id) {
        return resenaService.obtenerResenaPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResenaDTO> crearResena(@RequestBody ResenaCreacionDTO resenaCreacionDTO) {
        ResenaDTO nuevaResena = resenaService.agregarResena(resenaCreacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaResena);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResenaDTO> actualizarResena(@PathVariable Long id, @RequestBody ResenaDTO resenaDTO) {
        ResenaDTO resenaActualizada = resenaService.actualizarResena(id, resenaDTO);
        return ResponseEntity.ok(resenaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResena(@PathVariable Long id) {
        resenaService.eliminarResena(id);
        return ResponseEntity.noContent().build();
    }
}
