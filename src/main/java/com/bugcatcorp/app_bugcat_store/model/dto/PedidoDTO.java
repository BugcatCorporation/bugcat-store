package com.bugcatcorp.app_bugcat_store.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PedidoDTO {
    private LocalDateTime fechaPedido;
    private String estado;
    private Double total;
    private String direccionEnvio;
}
