package com.bugcatcorp.app_bugcat_store.model.entity;

import com.bugcatcorp.app_bugcat_store.validacion.EmailFormatConstraint;
import com.bugcatcorp.app_bugcat_store.validacion.TelefonoConstraint;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idproveedor;

    private String nombre;
    private String direccion;

    @EmailFormatConstraint
    @Column(unique = true)
    private String email;

    @TelefonoConstraint
    @Column(unique = true)
    private Integer celular;

    @ManyToMany(mappedBy = "proveedores")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Producto> productos;
}
