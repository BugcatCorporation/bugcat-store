package com.bugcatcorp.app_bugcat_store.model.dto;

import lombok.Data;

@Data
public class ProductoUpdateDTO {
    private Double precio;
    private Integer stock;
    private String imagen;
    private Boolean activo;
}
