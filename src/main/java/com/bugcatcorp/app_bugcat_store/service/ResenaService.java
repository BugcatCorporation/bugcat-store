package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.ResenaDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.ResenaUpdateDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Producto;
import com.bugcatcorp.app_bugcat_store.model.entity.Resena;
import com.bugcatcorp.app_bugcat_store.model.entity.Usuario;
import com.bugcatcorp.app_bugcat_store.repository.ProductoRepository;
import com.bugcatcorp.app_bugcat_store.repository.ResenaRepository;
import com.bugcatcorp.app_bugcat_store.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class ResenaService implements IResenaService {

    private ResenaRepository resenaRepository;
    private ProductoRepository productoRepository;
    private UsuarioRepository usuarioRepository;

    @Override
    public Resena crearResena(ResenaDTO resenaDTO) {
        log.info("Creando nueva reseña: {}", resenaDTO);
        Optional<Producto> optionalProducto = productoRepository.findById(resenaDTO.getIdProducto());
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(resenaDTO.getIdUsuario());

        if (optionalProducto.isPresent() && optionalUsuario.isPresent()) {
            Resena resena = new Resena();
            resena.setCalificacion(resenaDTO.getCalificacion());
            resena.setComentario(resenaDTO.getComentario());
            resena.setFechaResena(LocalDateTime.now());
            resena.setProducto(optionalProducto.get());
            resena.setUsuario(optionalUsuario.get());
            return resenaRepository.save(resena);
        } else {
            log.error("No se encontró el producto con ID {} o el usuario con ID {}", resenaDTO.getIdProducto(), resenaDTO.getIdUsuario());
            return null;
        }
    }

    @Override
    public Resena actualizarResena(Long idResena, ResenaUpdateDTO resenaUpdateDTO) {
        log.info("Actualizando reseña con ID {}: {}", idResena, resenaUpdateDTO);
        Optional<Resena> optionalResena = resenaRepository.findById(idResena);
        if (optionalResena.isPresent()) {
            Resena resena = optionalResena.get();
            resena.setCalificacion(resenaUpdateDTO.getCalificacion());
            resena.setComentario(resenaUpdateDTO.getComentario());
            return resenaRepository.save(resena);
        } else {
            log.error("No se encontró la reseña con ID {}", idResena);
            return null;
        }
    }

    @Override
    public void eliminarResena(Long idResena) {
        log.info("Eliminando reseña con ID {}", idResena);
        resenaRepository.deleteById(idResena);
    }
}
