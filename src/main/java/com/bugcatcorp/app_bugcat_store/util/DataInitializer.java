package com.bugcatcorp.app_bugcat_store.util;

import com.bugcatcorp.app_bugcat_store.model.entity.*;
import com.bugcatcorp.app_bugcat_store.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataInitializer {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;



    @PostConstruct
    public void init() {
        initRoles();
        initCategorias();
        initProductos();
        initProveedores();
        initResenas();
        initPedidos();
    }

    private void initRoles() {
        if (rolRepository.count() == 0) {
            rolRepository.save(new Rol("ROLE_USER"));
            rolRepository.save(new Rol("ROLE_ADMIN"));
        }
    }

    private void initCategorias() {
        if (categoriaRepository.count() == 0) {
            categoriaRepository.save(new Categoria("Llaveros", "Llaveros Bugcat"));
            categoriaRepository.save(new Categoria("Peluches", "Peluches Bugcat"));
            categoriaRepository.save(new Categoria("Tazas", "Tazas Bugcat"));
            categoriaRepository.save(new Categoria("Polos", "Polos Bugcat"));
            categoriaRepository.save(new Categoria("Mascarillas", "Mascarillas Bugcat"));
            categoriaRepository.save(new Categoria("Cartas", "Cartas Bugcat"));
            categoriaRepository.save(new Categoria("Figuras", "Figuras Bugcat"));
            categoriaRepository.save(new Categoria("Pegatinas", "Pegatinas Bugcat"));
        }
    }

    private void initProductos() {
        if (productoRepository.count() == 0) {
            Optional<Categoria> categoriaTazas = categoriaRepository.findById(3L);
            categoriaTazas.ifPresent(categoria -> {
                productoRepository.save(new Producto(null, "Taza mágica", "Taza que cambia de color con la temperatura", 12.99, 150, "https://taiwanderers.com/wp-content/uploads/2024/04/bugcat-capoo-house-7-635x800.jpg", true, categoria, null, null));
                productoRepository.save(new Producto(null, "Taza de cerámica", "Taza de cerámica con diseño de Bugcat", 9.99, 200, "https://taiwanderers.com/wp-content/uploads/2024/04/bugcat-capoo-house-36-635x800.jpg", true, categoria, null, null));
                productoRepository.save(new Producto(null, "Laptop", "Laptop de alto rendimiento", 1500.00, 10, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5-n8vFlJXOPB1QotVW3r5_G_JPGVCbBuDvw&s", true, null, null, null));
                productoRepository.save(new Producto(null, "Smartphone", "Último modelo de smartphone", 800.00, 15, "https://ae01.alicdn.com/kf/Sb6bc4caea186463aa1db466292b0f564O.jpg_640x640Q90.jpg_.webp", true, null, null, null));
                productoRepository.save(new Producto(null, "Tablet", "Tablet para todas tus necesidades", 600.00, 5, "https://ih1.redbubble.net/image.3589938603.7405/mwo,x1000,ipad_2_skin-pad,1000x1000,f8f8f8.webp", true, null, null, null));
            });
        }
    }

    private void initProveedores() {
        if (proveedorRepository.count() == 0) {
            Proveedor proveedor1 = new Proveedor(null, "Proveedor A", "Calle Falsa 123", "proveedorA@example.com", "999888777", new HashSet<>());
            Proveedor proveedor2 = new Proveedor(null, "Proveedor B", "Calle Real 456", "proveedorB@example.com", "999777666", new HashSet<>());
            proveedorRepository.save(proveedor1);
            proveedorRepository.save(proveedor2);
        }
    }

    private void initResenas() {
        if (resenaRepository.count() == 0) {
            Optional<Producto> producto = productoRepository.findById(1L);
            Optional<Usuario> usuario = usuarioRepository.findById(1L);

            if (producto.isPresent() && usuario.isPresent()) {
                Resena resena1 = new Resena(null, 5, "Excelente producto, muy recomendado!", LocalDateTime.now(), producto.get(), usuario.get());
                Resena resena2 = new Resena(null, 4, "Buen producto, pero la entrega se retrasó.", LocalDateTime.now(), producto.get(), usuario.get());
                resenaRepository.save(resena1);
                resenaRepository.save(resena2);
            }
        }
    }

    private void initPedidos() {
        if (pedidoRepository.count() == 0) {
            Optional<Usuario> usuario = usuarioRepository.findById(1L);
            if (usuario.isPresent()) {
                Pedido pedido1 = new Pedido(null, LocalDateTime.now(), "En Proceso", 100.50, "1234 Calle Falsa", new HashSet<>(), new HashSet<>(), usuario.get());
                Pedido pedido2 = new Pedido(null, LocalDateTime.now(), "Completado", 200.75, "5678 Calle Real", new HashSet<>(), new HashSet<>(), usuario.get());
                pedidoRepository.save(pedido1);
                pedidoRepository.save(pedido2);

                Optional<Producto> producto = productoRepository.findById(1L);
                if (producto.isPresent()) {
                    Set<DetallePedido> detallesPedido1 = new HashSet<>();
                    detallesPedido1.add(new DetallePedido(null, 2, 12.99, pedido1, producto.get()));
                    detallesPedido1.add(new DetallePedido(null, 1, 9.99, pedido1, producto.get()));

                    Set<DetallePedido> detallesPedido2 = new HashSet<>();
                    detallesPedido2.add(new DetallePedido(null, 1, 1500.00, pedido2, producto.get()));
                    detallesPedido2.add(new DetallePedido(null, 1, 800.00, pedido2, producto.get()));

                    pedido1.setDetalles(detallesPedido1);
                    pedido2.setDetalles(detallesPedido2);
                    pedidoRepository.save(pedido1);
                    pedidoRepository.save(pedido2);
                }
            }
        }

    }
}


