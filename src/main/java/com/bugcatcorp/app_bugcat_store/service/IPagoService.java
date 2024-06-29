package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.PagoDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Pago;

import java.util.List;

public interface IPagoService {
    List<PagoDTO> listarPagos();
    PagoDTO obtenerPagoPorId(Long id);
}
