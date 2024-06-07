package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.entity.Carrito;
import com.bugcatcorp.app_bugcat_store.model.entity.Usuario;

import java.util.Optional;

public interface ICarritoService {
    Optional<Carrito> buscarPorUsuario(Usuario usuario);
    Carrito guardar(Carrito carrito);
}
