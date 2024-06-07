package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.DetallePedidoDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.DetallePedido;

public interface IDetallePedidoService {
    DetallePedido crearDetallePedido(DetallePedidoDTO detallePedidoDTO);
    void eliminarDetallePedido(Long idDetallePedido);
}
