package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.DetalleCarritoDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.DetalleCarrito;
import com.bugcatcorp.app_bugcat_store.repository.DetalleCarritoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DetalleCarritoService implements IDetalleCarritoService {

    private DetalleCarritoRepository detalleCarritoRepository;

    @Override
    public DetalleCarrito crearDetalleCarrito(DetalleCarritoDTO detalleCarritoDTO) {
        DetalleCarrito detalleCarrito = new DetalleCarrito();
        detalleCarrito.setCantidad(detalleCarritoDTO.getCantidad());
        return detalleCarritoRepository.save(detalleCarrito);
    }

    @Override
    public void eliminarDetalleCarrito(Long idDetalleCarrito) {
        detalleCarritoRepository.deleteById(idDetalleCarrito);
    }
}

