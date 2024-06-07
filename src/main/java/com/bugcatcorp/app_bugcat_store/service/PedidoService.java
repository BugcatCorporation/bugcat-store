package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.exception.EntityNotFoundException;
import com.bugcatcorp.app_bugcat_store.model.dto.PedidoDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.PedidoUpdateDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Pedido;
import com.bugcatcorp.app_bugcat_store.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class PedidoService implements IPedidoService {

    private PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> listarPedidos() {
        log.info("Listando todos los pedidos");
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> buscarPedidoPorId(Long idPedido) {
        log.info("Buscando pedido por ID: {}", idPedido);
        return pedidoRepository.findById(idPedido);
    }

    @Override
    public List<Pedido> buscarPorFechaPedido(LocalDateTime fechaPedido) {
        log.info("Buscando pedidos por fecha de pedido: {}", fechaPedido);
        return pedidoRepository.findByFechaPedido(fechaPedido);
    }

    @Override
    @Transactional
    public Pedido crearPedido(PedidoDTO pedidoDTO) {
        log.info("Creando nuevo pedido: {}", pedidoDTO);
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(pedidoDTO.getFechaPedido());
        pedido.setEstado(pedidoDTO.getEstado());
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDireccionEnvio(pedidoDTO.getDireccionEnvio());
        return pedidoRepository.save(pedido);
    }

    @Override
    @Transactional
    public Pedido actualizarPedido(Long id, PedidoUpdateDTO pedidoUpdateDTO) {
        log.info("Actualizando pedido con ID {}: {}", id, pedidoUpdateDTO);
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();
            pedido.setDireccionEnvio(pedidoUpdateDTO.getDireccionEnvio());
            return pedidoRepository.save(pedido);
        } else {
            log.warn("Pedido no encontrado con ID: {}", id);
            throw new EntityNotFoundException("Pedido no encontrado con ID: " + id);
        }
    }

    @Override
    @Transactional
    public void eliminarPedido(Long idPedido) {
        log.info("Eliminando pedido con ID: {}", idPedido);
        if (pedidoRepository.existsById(idPedido)) {
            pedidoRepository.deleteById(idPedido);
        } else {
            log.warn("Pedido no encontrado con ID: {}", idPedido);
            throw new EntityNotFoundException("Pedido no encontrado con ID: " + idPedido);
        }
    }
}
