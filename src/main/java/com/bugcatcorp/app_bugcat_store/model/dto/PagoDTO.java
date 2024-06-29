package com.bugcatcorp.app_bugcat_store.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PagoDTO {
    private Long idpago;
    private String metodoPago;
    private Double monto;
    private LocalDateTime fechaPago;
    private Long pedidoId;
}
