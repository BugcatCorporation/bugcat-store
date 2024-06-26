package com.bugcatcorp.app_bugcat_store.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoDTO {
    private Long idpedido;
    private LocalDateTime fechaPedido;
    private String estado;
    private Double total;
    private String direccionEnvio;
    private List<DetallePedidoDTO> detalles;
}
