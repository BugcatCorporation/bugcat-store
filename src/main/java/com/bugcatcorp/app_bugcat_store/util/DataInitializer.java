package com.bugcatcorp.app_bugcat_store.util;

import com.bugcatcorp.app_bugcat_store.repository.CategoriaRepository;
import com.bugcatcorp.app_bugcat_store.repository.ProductoRepository;
import com.bugcatcorp.app_bugcat_store.repository.RolRepository;
import com.bugcatcorp.app_bugcat_store.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {

    private RolRepository rolRepository;
    private UsuarioRepository usuarioRepository;
    private CategoriaRepository categoriaRepository;
    private ProductoRepository productoRepository;

}
