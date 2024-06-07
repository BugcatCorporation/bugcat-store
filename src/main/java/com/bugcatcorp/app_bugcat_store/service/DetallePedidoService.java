package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.DetallePedidoDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.DetallePedido;
import com.bugcatcorp.app_bugcat_store.repository.DetallePedidoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DetallePedidoService implements IDetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;

    @Override
    public DetallePedido crearDetallePedido(DetallePedidoDTO detallePedidoDTO) {
        DetallePedido detallePedido = new DetallePedido();
        detallePedido.setCantidad(detallePedidoDTO.getCantidad());
        detallePedido.setPrecio(detallePedidoDTO.getPrecio());
        detallePedido = detallePedidoRepository.save(detallePedido);
        log.info("Nuevo detalle de pedido creado: {}", detallePedido);
        return detallePedido;
    }

    @Override
    public void eliminarDetallePedido(Long idDetallePedido) {
        detallePedidoRepository.deleteById(idDetallePedido);
        log.info("Detalle de pedido con ID {} eliminado correctamente", idDetallePedido);
    }
}

