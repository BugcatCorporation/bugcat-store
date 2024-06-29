package com.bugcatcorp.app_bugcat_store.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoCreacionDTO {

    private LocalDateTime fechaPedido;
    private String estado;
    private Double total;
    private String direccionEnvio;
    private Long idUsuario;
    private List<DetallePedidoCreacionDTO> detalles;
}
