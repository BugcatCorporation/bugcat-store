package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.PedidoCreacionDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.PedidoDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IPedidoService {
    List<PedidoDTO> listarPedidos();
    Optional<PedidoDTO> obtenerPedidoPorId(Long id);
    PedidoDTO agregarPedido(PedidoCreacionDTO pedidoCreacionDTO);
    PedidoDTO actualizarPedido(Long id, PedidoDTO pedidoDTO);
    void eliminarPedido(Long id);
}
