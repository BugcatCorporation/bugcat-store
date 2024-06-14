package com.bugcatcorp.app_bugcat_store.util;

import com.bugcatcorp.app_bugcat_store.model.entity.Rol;
import com.bugcatcorp.app_bugcat_store.repository.CategoriaRepository;
import com.bugcatcorp.app_bugcat_store.repository.ProductoRepository;
import com.bugcatcorp.app_bugcat_store.repository.RolRepository;
import com.bugcatcorp.app_bugcat_store.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {

    @Autowired
    private RolRepository rolRepository;
    private UsuarioRepository usuarioRepository;
    private CategoriaRepository categoriaRepository;
    private ProductoRepository productoRepository;

    @PostConstruct
    public void intit() {
        if(rolRepository.count() == 0) {
            rolRepository.save(new Rol("ROLE_USER"));
            rolRepository.save(new Rol("ROLE_ADMIN"));
        }
    }
}
