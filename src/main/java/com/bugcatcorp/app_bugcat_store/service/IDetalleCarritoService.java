package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.DetalleCarritoDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.DetalleCarrito;

public interface IDetalleCarritoService {
    DetalleCarrito crearDetalleCarrito(DetalleCarritoDTO detalleCarritoDTO);
    void eliminarDetalleCarrito(Long idDetalleCarrito);
}
