package com.bugcatcorp.app_bugcat_store.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
public class Permiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermiso;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idrol")
    private Rol rol;
}
