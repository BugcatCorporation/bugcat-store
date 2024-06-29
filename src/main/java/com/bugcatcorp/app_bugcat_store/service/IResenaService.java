package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.ResenaCreacionDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.ResenaDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Resena;

import java.util.List;
import java.util.Optional;

public interface IResenaService {
    List<ResenaDTO> listarResenas();
    Optional<ResenaDTO> obtenerResenaPorId(Long id);
    ResenaDTO agregarResena(ResenaCreacionDTO resenaCreacionDTO);
    ResenaDTO actualizarResena(Long id, ResenaDTO resenaDTO);
    void eliminarResena(Long id);
}


