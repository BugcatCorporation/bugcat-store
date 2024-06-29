package com.bugcatcorp.app_bugcat_store.model.dto;

import lombok.Data;

@Data
public class ResenaCreacionDTO {
    private Integer calificacion;
    private String comentario;
    private Long idProducto;
    private Long idUsuario;
}