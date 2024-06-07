package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.PagoDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Pago;

public interface IPagoService {
    Pago crearPago(PagoDTO pagoDTO);
}
