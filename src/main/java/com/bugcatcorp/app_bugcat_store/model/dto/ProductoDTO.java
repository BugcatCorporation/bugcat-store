package com.bugcatcorp.app_bugcat_store.model.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String imagen;
    private Boolean activo;
}
