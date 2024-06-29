package com.bugcatcorp.app_bugcat_store.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetallePedidoDTO {
    private Long iddetallepedido;
    private Integer cantidad;
    private Double precio;
    private Long pedidoId;
    private Long productoId;
    private ProductoDTO producto;
}
