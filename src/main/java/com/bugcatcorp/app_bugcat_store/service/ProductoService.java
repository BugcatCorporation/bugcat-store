package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.exception.EntityNotFoundException;
import com.bugcatcorp.app_bugcat_store.model.dto.ProductoDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.ProductoUpdateDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Producto;
import com.bugcatcorp.app_bugcat_store.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ProductoService implements IProductoService {

    private final ProductoRepository pr;

    @Override
    public List<Producto> listarProductos() {
        log.info("Listando todos los productos");
        return pr.findAll();
    }

    @Override
    public Optional<Producto> buscarProductoPorId(Long id) {
        log.info("Buscando producto por ID: {}", id);
        return pr.findById(id);
    }

    @Override
    public Optional<Producto> buscarProductoPorNombre(String nombre) {
        log.info("Buscando producto por nombre: {}", nombre);
        return pr.findByNombre(nombre);
    }

    @Override
    public Optional<Producto> buscarProductoPorDescripcion(String descripcion) {
        log.info("Buscando producto por descripción: {}", descripcion);
        return pr.findByDescripcion(descripcion);
    }

    @Override
    public Optional<Producto> buscarProductoPorPrecio(Double precio) {
        log.info("Buscando producto por precio: {}", precio);
        return pr.findByPrecio(precio);
    }

    @Override
    @Transactional
    public Producto agregarProducto(ProductoDTO productoDTO) {
        log.info("Agregando nuevo producto: {}", productoDTO);
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setImagen(productoDTO.getImagen());
        producto.setActivo(productoDTO.getActivo());
        return pr.save(producto);
    }

    @Override
    @Transactional
    public Producto actualizarProducto(Long id, ProductoUpdateDTO productoUpdateDTO) {
        log.info("Actualizando producto con ID {}: {}", id, productoUpdateDTO);
        Optional<Producto> optionalProducto = pr.findById(id);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            producto.setPrecio(productoUpdateDTO.getPrecio());
            producto.setStock(productoUpdateDTO.getStock());
            producto.setImagen(productoUpdateDTO.getImagen());
            producto.setActivo(productoUpdateDTO.getActivo());
            return pr.save(producto);
        } else {
            throw new EntityNotFoundException("Producto no encontrado con ID: " + id);
        }
    }

    @Override
    @Transactional
    public void borrarProducto(Long id) {
        log.info("Borrando producto con ID: {}", id);
        if (pr.existsById(id)) {
            pr.deleteById(id);
        } else {
            throw new EntityNotFoundException("Producto no encontrado con ID: " + id);
        }
    }
}
