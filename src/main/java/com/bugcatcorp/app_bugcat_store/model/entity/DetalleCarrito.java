package com.bugcatcorp.app_bugcat_store.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detalle_carrito")
public class DetalleCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iddetalle;
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Producto producto;
}
