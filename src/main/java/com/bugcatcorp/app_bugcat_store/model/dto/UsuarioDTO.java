package com.bugcatcorp.app_bugcat_store.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {
    private Long idusuario;
    private String nombre;
    private String email;
    private String username;
    private String contrasena;
    private String direccion;
    private String telefono;
    private Boolean activo;
}