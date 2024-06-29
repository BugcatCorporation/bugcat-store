package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.exception.EntityNotFoundException;
import com.bugcatcorp.app_bugcat_store.model.dto.ResenaCreacionDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.ResenaDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Producto;
import com.bugcatcorp.app_bugcat_store.model.entity.Resena;
import com.bugcatcorp.app_bugcat_store.model.entity.Usuario;
import com.bugcatcorp.app_bugcat_store.repository.ProductoRepository;
import com.bugcatcorp.app_bugcat_store.repository.ResenaRepository;
import com.bugcatcorp.app_bugcat_store.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResenaService implements IResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<ResenaDTO> listarResenas() {
        return resenaRepository.findAll().stream()
                .map(this::convertirAResenaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ResenaDTO> obtenerResenaPorId(Long id) {
        Resena resena = resenaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reseña no encontrada con el id " + id));
        return Optional.of(convertirAResenaDTO(resena));
    }

    @Override
    public ResenaDTO agregarResena(ResenaCreacionDTO resenaCreacionDTO) {
        Resena resena = new Resena();
        resena.setCalificacion(resenaCreacionDTO.getCalificacion());
        resena.setComentario(resenaCreacionDTO.getComentario());
        resena.setFechaResena(LocalDateTime.now()); // Inicializando fechaResena con la fecha actual

        Producto producto = productoRepository.findById(resenaCreacionDTO.getIdProducto())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con el id " + resenaCreacionDTO.getIdProducto()));
        resena.setProducto(producto);

        Usuario usuario = usuarioRepository.findById(resenaCreacionDTO.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con el id " + resenaCreacionDTO.getIdUsuario()));
        resena.setUsuario(usuario);

        resena = resenaRepository.save(resena);
        return convertirAResenaDTO(resena);
    }

    @Override
    public ResenaDTO actualizarResena(Long id, ResenaDTO resenaDTO) {
        Resena resena = resenaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reseña no encontrada con el id " + id));
        resena.setCalificacion(resenaDTO.getCalificacion());
        resena.setComentario(resenaDTO.getComentario());
        resena.setFechaResena(LocalDateTime.parse(resenaDTO.getFechaResena()));

        Producto producto = productoRepository.findById(resenaDTO.getIdProducto())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con el id " + resenaDTO.getIdProducto()));
        resena.setProducto(producto);

        Usuario usuario = usuarioRepository.findById(resenaDTO.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con el id " + resenaDTO.getIdUsuario()));
        resena.setUsuario(usuario);

        resena = resenaRepository.save(resena);
        return convertirAResenaDTO(resena);
    }

    @Override
    public void eliminarResena(Long id) {
        if (!resenaRepository.existsById(id)) {
            throw new EntityNotFoundException("Reseña no encontrada con el id " + id);
        }
        resenaRepository.deleteById(id);
    }

    private ResenaDTO convertirAResenaDTO(Resena resena) {
        ResenaDTO dto = new ResenaDTO();
        dto.setCalificacion(resena.getCalificacion());
        dto.setComentario(resena.getComentario());
        dto.setFechaResena(resena.getFechaResena().toString());
        dto.setIdProducto(resena.getProducto().getIdproducto());
        dto.setIdUsuario(resena.getUsuario().getIdusuario());
        return dto;
    }
}
