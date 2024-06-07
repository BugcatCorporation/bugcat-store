package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.entity.Carrito;
import com.bugcatcorp.app_bugcat_store.model.entity.Usuario;
import com.bugcatcorp.app_bugcat_store.repository.CarritoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CarritoService implements ICarritoService{

    private CarritoRepository carritoRepository;

    @Override
    public Optional<Carrito> buscarPorUsuario(Usuario usuario) {
        log.info("Buscando carrito para el usuario: {}", usuario.getNombre());
        Optional<Carrito> carrito = carritoRepository.findByUsuario(usuario);
        if (carrito.isPresent()) {
            log.info("Carrito encontrado para el usuario: {}", usuario.getNombre());
        } else {
            log.warn("No se encontró carrito para el usuario: {}", usuario.getNombre());
        }
        return carrito;
    }

    @Override
    public Carrito guardar(Carrito carrito) {
        log.info("Guardando carrito para el usuario: {}", carrito.getUsuario().getNombre());
        Carrito savedCarrito = carritoRepository.save(carrito);
        log.info("Carrito guardado con ID: {}", savedCarrito.getIdcarrito());
        return savedCarrito;
    }
}
