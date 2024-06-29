package com.bugcatcorp.app_bugcat_store.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoCreacionDTO {
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String imagen;
    private Boolean activo;
    private Long idCategoria;
}
