package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.exception.EntityNotFoundException;
import com.bugcatcorp.app_bugcat_store.model.dto.*;
import com.bugcatcorp.app_bugcat_store.model.entity.DetallePedido;
import com.bugcatcorp.app_bugcat_store.model.entity.Pedido;
import com.bugcatcorp.app_bugcat_store.model.entity.Producto;
import com.bugcatcorp.app_bugcat_store.model.entity.Usuario;
import com.bugcatcorp.app_bugcat_store.repository.PedidoRepository;
import com.bugcatcorp.app_bugcat_store.repository.ProductoRepository;
import com.bugcatcorp.app_bugcat_store.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PedidoService implements IPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional
    public PedidoDTO agregarPedido(PedidoCreacionDTO pedidoCreacionDTO) {
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(pedidoCreacionDTO.getFechaPedido());
        pedido.setEstado(pedidoCreacionDTO.getEstado());
        pedido.setTotal(pedidoCreacionDTO.getTotal());
        pedido.setDireccionEnvio(pedidoCreacionDTO.getDireccionEnvio());

        // Obtener el Usuario persistido desde la base de datos
        Usuario usuario;
        if (pedidoCreacionDTO.getIdUsuario() == null) {
            usuario = usuarioRepository.findById(2L)
                    .orElseThrow(() -> new EntityNotFoundException("Usuario con id 2 no encontrado"));
        } else {
            usuario = usuarioRepository.findById(pedidoCreacionDTO.getIdUsuario())
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        }

        pedido.setUsuario(usuario); // Asignar el Usuario persistido al Pedido

        Set<DetallePedido> detalles = new HashSet<>();
        for (DetallePedidoCreacionDTO dp : pedidoCreacionDTO.getDetalles()) {
            Producto producto = productoRepository.findById(dp.getProductoId())
                    .orElseThrow(() -> new EntityNotFoundException("Producto con id " + dp.getProductoId() + " no encontrado"));

            DetallePedido detalle = new DetallePedido();
            detalle.setProducto(producto);
            detalle.setCantidad(dp.getCantidad());
            detalle.setPrecio(dp.getPrecio());
            detalle.setPedido(pedido);
            detalles.add(detalle);
        }

        pedido.setDetalles(detalles);
        pedido = pedidoRepository.save(pedido);

        return convertirAPedidoDTO(pedido);
    }

    private PedidoDTO convertirAPedidoDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setIdpedido(pedido.getIdpedido());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setEstado(pedido.getEstado());
        dto.setTotal(pedido.getTotal());
        dto.setDireccionEnvio(pedido.getDireccionEnvio());

        if (pedido.getDetalles() != null) {
            dto.setDetalles(pedido.getDetalles().stream()
                    .map(this::convertirADetallePedidoDTO)
                    .collect(Collectors.toList()));
        } else {
            dto.setDetalles(new ArrayList<>());
        }

        return dto;
    }

    private DetallePedidoDTO convertirADetallePedidoDTO(DetallePedido detalle) {
        DetallePedidoDTO dto = new DetallePedidoDTO();
        dto.setIddetallepedido(detalle.getIddetallepedido());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecio(detalle.getPrecio());
        dto.setProductoId(detalle.getProducto().getIdproducto());
        dto.setProducto(convertirAProductoDTO(detalle.getProducto()));
        return dto;
    }

    private ProductoDTO convertirAProductoDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setDescripcion(producto.getDescripcion());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setStock(producto.getStock());
        productoDTO.setImagen(producto.getImagen());
        return productoDTO;
    }
}
