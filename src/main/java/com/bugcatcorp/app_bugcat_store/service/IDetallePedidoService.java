package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.DetallePedidoCreacionDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.DetallePedidoDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.DetallePedido;

import java.util.List;
import java.util.Optional;

public interface IDetallePedidoService {
    DetallePedidoDTO createDetallePedido(DetallePedidoCreacionDTO detallePedidoDTO);
    Optional<DetallePedidoDTO> getDetallePedidoById(Long id);
    DetallePedidoDTO updateDetallePedido(Long id, DetallePedidoDTO detallePedidoDTO);
    void deleteDetallePedido(Long id);
    List<DetallePedidoDTO> listAllDetallePedidos();
}
