package com.bugcatcorp.app_bugcat_store.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idrol;

    @Column(nullable = false, unique = true)
    private String rol;
    private String descripcion;

    @ManyToMany(mappedBy = "roles")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Usuario> usuarios;
}
