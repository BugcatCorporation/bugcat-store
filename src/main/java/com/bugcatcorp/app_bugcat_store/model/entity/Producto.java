package com.bugcatcorp.app_bugcat_store.model.entity;

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
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idproducto;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String imagen;
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "idcategoria")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Categoria categoria;

    @ManyToMany
    @JoinTable(
            name = "producto_proveedor",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "proveedor_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Proveedor> proveedores;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Resena> resenas;
}
