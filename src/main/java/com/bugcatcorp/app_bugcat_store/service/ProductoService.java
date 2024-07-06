package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.exception.EntityNotFoundException;
import com.bugcatcorp.app_bugcat_store.model.dto.ProductoCreacionDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.ProductoDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Categoria;
import com.bugcatcorp.app_bugcat_store.model.entity.Producto;
import com.bugcatcorp.app_bugcat_store.repository.CategoriaRepository;
import com.bugcatcorp.app_bugcat_store.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<ProductoDTO> listarTodosLosProductos() {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoDTO> productosDTO = new ArrayList<>();
        for (Producto producto : productos) {
            productosDTO.add(convertirAProductoDTO(producto));
        }
        return productosDTO;
    }

    @Override
    public Optional<ProductoDTO> obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con el id " + id));
        return Optional.of(convertirAProductoDTO(producto));
    }


    @Override
    public Optional<ProductoDTO> agregarProducto(ProductoCreacionDTO productoCreacionDTO) {
        Categoria categoria = categoriaRepository.findById(productoCreacionDTO.getIdCategoria())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con el id " + productoCreacionDTO.getIdCategoria()));

        Producto producto = new Producto();
        producto.setNombre(productoCreacionDTO.getNombre());
        producto.setDescripcion(productoCreacionDTO.getDescripcion());
        producto.setPrecio(productoCreacionDTO.getPrecio());
        producto.setStock(productoCreacionDTO.getStock());
        producto.setImagen(productoCreacionDTO.getImagen());
        producto.setActivo(productoCreacionDTO.getActivo());
        producto.setCategoria(categoria);
        producto = productoRepository.save(producto);
        return Optional.of(convertirAProductoDTO(producto));
    }

    @Override
    public Optional<ProductoDTO> actualizarProducto(Long id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con el id " + id));
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setImagen(productoDTO.getImagen());
        producto.setActivo(productoDTO.getActivo());
        producto.setCategoria(categoriaRepository.findById(productoDTO.getIdCategoria())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con el id " + productoDTO.getIdCategoria())));


        producto = productoRepository.save(producto);
        return Optional.of(convertirAProductoDTO(producto));
    }

    private ProductoDTO convertirAProductoDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdproducto(producto.getIdproducto());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setImagen(producto.getImagen());
        dto.setActivo(producto.getActivo());
        dto.setIdCategoria(producto.getCategoria().getIdcategoria());
        return dto;
    }
}
