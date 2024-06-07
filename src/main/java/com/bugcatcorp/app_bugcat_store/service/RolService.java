package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.exception.EntityNotFoundException;
import com.bugcatcorp.app_bugcat_store.model.dto.RolDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Rol;
import com.bugcatcorp.app_bugcat_store.repository.RolRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class RolService implements IRolService {

    private RolRepository rolRepository;

    @Override
    public List<Rol> listarRoles() {
        log.info("Listando todos los roles");
        return rolRepository.findAll();
    }

    @Override
    public Optional<Rol> buscarRolPorId(Long id) {
        log.info("Buscando rol por ID: {}", id);
        return rolRepository.findById(id);
    }

    @Override
    public Optional<Rol> buscarRolPorNombre(String rol) {
        log.info("Buscando rol por nombre: {}", rol);
        return rolRepository.findByRol(rol);
    }

    @Override
    public Rol agregarRol(RolDTO rolDTO) {
        log.info("Agregando nuevo rol: {}", rolDTO);
        Rol rol = new Rol();
        rol.setRol(rolDTO.getRol());
        rol.setDescripcion(rolDTO.getDescripcion());
        return rolRepository.save(rol);
    }

    @Override
    public Rol actualizarRol(Long id, RolDTO rolDTO) {
        log.info("Actualizando rol con ID {}: {}", id, rolDTO);
        Optional<Rol> optionalRol = rolRepository.findById(id);
        if (optionalRol.isPresent()) {
            Rol rol = optionalRol.get();
            rol.setRol(rolDTO.getRol());
            rol.setDescripcion(rolDTO.getDescripcion());
            return rolRepository.save(rol);
        } else {
            log.warn("Rol no encontrado con ID: {}", id);
            throw new EntityNotFoundException("Rol no encontrado con ID: " + id);
        }
    }

    @Override
    public void borrarRol(Long id) {
        log.info("Borrando rol con ID: {}", id);
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
        } else {
            log.warn("Rol no encontrado con ID: {}", id);
            throw new EntityNotFoundException("Rol no encontrado con ID: " + id);
        }
    }
}
