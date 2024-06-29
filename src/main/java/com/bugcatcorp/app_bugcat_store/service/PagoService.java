package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.PagoDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Pago;
import com.bugcatcorp.app_bugcat_store.repository.PagoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoService implements IPagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public List<PagoDTO> listarPagos() {
        return pagoRepository.findAll().stream()
                .map(this::convertirAPagoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PagoDTO obtenerPagoPorId(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago not found"));
        return convertirAPagoDTO(pago);
    }

    private PagoDTO convertirAPagoDTO(Pago pago) {
        PagoDTO dto = new PagoDTO();
        dto.setIdpago(pago.getIdpago());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setMonto(pago.getMonto());
        dto.setFechaPago(pago.getFechaPago());
        dto.setPedidoId(pago.getPedido().getIdpedido());
        return dto;
    }
}
