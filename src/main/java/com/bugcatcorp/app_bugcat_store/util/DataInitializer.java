package com.bugcatcorp.app_bugcat_store.util;

import com.bugcatcorp.app_bugcat_store.model.entity.Categoria;
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
public class DataInitializer {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @PostConstruct
    public void intit() {
        if(rolRepository.count() == 0) {
            rolRepository.save(new Rol("ROLE_USER"));
            rolRepository.save(new Rol("ROLE_ADMIN"));
        }

        if(categoriaRepository.count() == 0){
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
}
