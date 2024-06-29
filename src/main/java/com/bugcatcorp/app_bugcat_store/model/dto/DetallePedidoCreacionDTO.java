package com.bugcatcorp.app_bugcat_store.model.dto;

import lombok.Data;

@Data
public class DetallePedidoCreacionDTO {

    private Integer cantidad;
    private Double precio;
    private Long pedidoId;
    private Long productoId;
}
