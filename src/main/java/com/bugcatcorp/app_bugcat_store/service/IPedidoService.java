package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.PedidoDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.PedidoUpdateDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IPedidoService {
    List<Pedido> listarPedidos();
    Optional<Pedido> buscarPedidoPorId(Long idPedido);
    List<Pedido> buscarPorFechaPedido(LocalDateTime fechaPedido);
    Pedido crearPedido(PedidoDTO pedidoDTO);
    Pedido actualizarPedido(Long id, PedidoUpdateDTO pedidoUpdateDTO);
    void eliminarPedido(Long idPedido);
}
