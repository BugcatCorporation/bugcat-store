package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.ResenaDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.ResenaUpdateDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Resena;

public interface IResenaService {
    Resena crearResena(ResenaDTO resenaDTO);
    Resena actualizarResena(Long idResena, ResenaUpdateDTO resenaUpdateDTO);
    void eliminarResena(Long idResena);
}


