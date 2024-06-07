package com.bugcatcorp.app_bugcat_store.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpago;
    private String metodoPago;
    private Double monto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private LocalDateTime fechaPago;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Pedido pedido;
}
