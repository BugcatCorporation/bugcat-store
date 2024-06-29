package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.ProductoCreacionDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.ProductoDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.ProductoUpdateDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<ProductoDTO> listarTodosLosProductos();
    Optional<ProductoDTO> obtenerProductoPorId(Long id);
    Optional<ProductoDTO> agregarProducto(ProductoCreacionDTO productoCreacionDTO);
    Optional<ProductoDTO> actualizarProducto(Long id, ProductoDTO productoDTO);
}
