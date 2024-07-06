package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.DetallePedidoCreacionDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.DetallePedidoDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.ProductoDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.DetallePedido;
import com.bugcatcorp.app_bugcat_store.model.entity.Pedido;
import com.bugcatcorp.app_bugcat_store.model.entity.Producto;
import com.bugcatcorp.app_bugcat_store.repository.DetallePedidoRepository;
import com.bugcatcorp.app_bugcat_store.repository.PedidoRepository;
import com.bugcatcorp.app_bugcat_store.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class DetallePedidoService implements IDetallePedidoService {


    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public DetallePedidoDTO createDetallePedido(DetallePedidoCreacionDTO detallePedidoDTO) {
        // Fetch the Pedido entity
        Pedido pedido = pedidoRepository.findById(detallePedidoDTO.getPedidoId()).orElseThrow();

        // Fetch the Producto entity
        Producto producto = productoRepository.findById(detallePedidoDTO.getProductoId()).orElseThrow(() ->
                new RuntimeException("Producto with id " + detallePedidoDTO.getProductoId() + " not found"));

        // Create DetallePedido entity
        DetallePedido detallePedido = new DetallePedido();
        detallePedido.setCantidad(detallePedidoDTO.getCantidad());
        detallePedido.setPrecio(detallePedidoDTO.getPrecio());
        detallePedido.setPedido(pedido);
        detallePedido.setProducto(producto);

        // Save DetallePedido entity
        detallePedido = detallePedidoRepository.save(detallePedido);
        return convertToDTO(detallePedido);
    }


    @Override
    public Optional<DetallePedidoDTO> getDetallePedidoById(Long id) {
        return detallePedidoRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public DetallePedidoDTO updateDetallePedido(Long id, DetallePedidoDTO detallePedidoDTO) {
        DetallePedido detallePedido = detallePedidoRepository.findById(id).orElseThrow();
        detallePedido.setCantidad(detallePedidoDTO.getCantidad());
        detallePedido.setPrecio(detallePedidoDTO.getPrecio());
        detallePedido = detallePedidoRepository.save(detallePedido);
        return convertToDTO(detallePedido);
    }

    @Override
    public void deleteDetallePedido(Long id) {
        detallePedidoRepository.deleteById(id);
    }

    @Override
    public List<DetallePedidoDTO> listAllDetallePedidos() {
        return detallePedidoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private DetallePedidoDTO convertToDTO(DetallePedido detallePedido) {
        DetallePedidoDTO dto = new DetallePedidoDTO();
        dto.setIddetallepedido(detallePedido.getIddetallepedido());
        dto.setCantidad(detallePedido.getCantidad());
        dto.setPrecio(detallePedido.getPrecio());
        dto.setPedidoId(detallePedido.getPedido().getIdpedido());
        dto.setProducto(convertirAProductoDTO(detallePedido.getProducto()));
        return dto;
    }

    private ProductoDTO convertirAProductoDTO(Producto producto) {
        if (producto == null) {
            return null;
        }
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setIdproducto(producto.getIdproducto());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setDescripcion(producto.getDescripcion());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setStock(producto.getStock());
        productoDTO.setImagen(producto.getImagen());
        productoDTO.setActivo(producto.getActivo());
        return productoDTO;
    }
}

