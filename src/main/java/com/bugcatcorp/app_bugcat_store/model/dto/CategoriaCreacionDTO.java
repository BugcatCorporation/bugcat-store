package com.bugcatcorp.app_bugcat_store.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaCreacionDTO {

    private String nombre;
    private String descripcion;
}
