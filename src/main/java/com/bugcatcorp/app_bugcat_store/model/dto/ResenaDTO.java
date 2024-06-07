package com.bugcatcorp.app_bugcat_store.model.dto;

import lombok.Data;

@Data
public class ResenaDTO {
    private Integer calificacion;
    private String comentario;
    private String fechaResena;
    private Long idProducto;
    private Long idUsuario;
}
