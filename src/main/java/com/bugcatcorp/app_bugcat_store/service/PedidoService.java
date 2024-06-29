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
import org.hibernate.Hibernate;
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
    @Transactional(readOnly = true)
    public List<PedidoDTO> listarPedidos() {
        return pedidoRepository.findAll().stream()
                .map(this::convertirAPedidoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PedidoDTO> obtenerPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        Hibernate.initialize(pedido.getDetalles());
        return Optional.of(convertirAPedidoDTO(pedido));
    }


    @Override
    @Transactional
    public PedidoDTO agregarPedido(PedidoCreacionDTO pedidoCreacionDTO) {
        Pedido pedido = new Pedido();
        pedido.setFechaPedido(pedidoCreacionDTO.getFechaPedido());
        pedido.setEstado(pedidoCreacionDTO.getEstado());
        pedido.setTotal(pedidoCreacionDTO.getTotal());
        pedido.setDireccionEnvio(pedidoCreacionDTO.getDireccionEnvio());

        Usuario usuario = usuarioRepository.findById(pedidoCreacionDTO.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con el id " + pedidoCreacionDTO.getIdUsuario()));
        pedido.setUsuario(usuario);

        Set<DetallePedido> detalles = new HashSet<>();
        for (DetallePedidoCreacionDTO dp : pedidoCreacionDTO.getDetalles()) {
            Producto producto = productoRepository.findById(dp.getProductoId())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

            if (producto.getStock() < dp.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - dp.getCantidad());
            productoRepository.save(producto);

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

    @Override
    public PedidoDTO actualizarPedido(Long id, PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado con el id " + id));
        pedido.setFechaPedido(pedidoDTO.getFechaPedido());
        pedido.setEstado(pedidoDTO.getEstado());
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDireccionEnvio(pedidoDTO.getDireccionEnvio());

        pedido = pedidoRepository.save(pedido);
        return convertirAPedidoDTO(pedido);
    }

    @Override
    public void eliminarPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pedido no encontrado con el id " + id);
        }
        pedidoRepository.deleteById(id);
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
