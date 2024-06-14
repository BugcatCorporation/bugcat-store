package com.bugcatcorp.app_bugcat_store.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resena")
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idresena;

    @Min(value = 1, message = "La calificación minima es 1")
    @Max(value = 5, message = "La calificación maxima es 5")
    private Integer calificacion;

    private String comentario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime fechaResena;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Usuario usuario;
}
