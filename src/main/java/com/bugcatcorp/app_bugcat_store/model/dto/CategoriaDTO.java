package com.bugcatcorp.app_bugcat_store.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDTO {
    private Long idcategoria;
    private String nombre;
    private String descripcion;
}
