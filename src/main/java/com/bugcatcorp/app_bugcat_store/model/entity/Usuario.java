package com.bugcatcorp.app_bugcat_store.model.entity;

import com.bugcatcorp.app_bugcat_store.validacion.EmailFormatConstraint;
import com.bugcatcorp.app_bugcat_store.validacion.TelefonoConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idusuario;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @EmailFormatConstraint
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String contrasena;
    private String direccion;

    @TelefonoConstraint
    @NotBlank(message = "El telefono es obligatorio")
    @Column(unique = true)
    private String telefono;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    private Boolean activo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Resena> resenas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Carrito> carritos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Pedido> pedidos;

    @ManyToMany
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "idusuario"),
            inverseJoinColumns = @JoinColumn(name = "idrol")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Rol> roles;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = new Date();
    }
}
