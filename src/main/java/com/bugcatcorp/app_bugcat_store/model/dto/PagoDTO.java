package com.bugcatcorp.app_bugcat_store.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PagoDTO {
    private String metodoPago;
    private Double monto;
    private LocalDateTime fechaPago;
}
