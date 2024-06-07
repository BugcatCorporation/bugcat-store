package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.ProductoDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.ProductoUpdateDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> listarProductos();
    Optional<Producto> buscarProductoPorId(Long id);
    Optional<Producto> buscarProductoPorNombre(String nombre);
    Optional<Producto> buscarProductoPorDescripcion(String descripcion);
    Optional<Producto> buscarProductoPorPrecio(Double precio);
    Producto agregarProducto(ProductoDTO productoDTO);
    Producto actualizarProducto(Long id, ProductoUpdateDTO productoUpdateDTO);
    void borrarProducto(Long id);
}
