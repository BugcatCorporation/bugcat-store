package com.bugcatcorp.app_bugcat_store.model.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String nombre;
    private String email;
    private String contrasena;
    private String direccion;
    private String telefono;
    private Boolean activo;
}