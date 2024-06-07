package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.PagoDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Pago;
import com.bugcatcorp.app_bugcat_store.repository.PagoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
@Slf4j
public class PagoService implements IPagoService {

    private PagoRepository pagoRepository;

    @Override
    public Pago crearPago(PagoDTO pagoDTO) {
        log.info("Creando pago: {}", pagoDTO);
        Pago pago = new Pago();
        pago.setMetodoPago(pagoDTO.getMetodoPago());
        pago.setMonto(pagoDTO.getMonto());
        pago.setFechaPago(LocalDateTime.now());
        return pagoRepository.save(pago);
    }
}
