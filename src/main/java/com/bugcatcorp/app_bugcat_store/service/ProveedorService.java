package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.exception.EntityNotFoundException;
import com.bugcatcorp.app_bugcat_store.model.dto.ProveedorDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Proveedor;
import com.bugcatcorp.app_bugcat_store.repository.ProveedorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProveedorService implements IProveedorService {

    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> listarProveedores() {
        log.info("Listando todos los proveedores");
        return proveedorRepository.findAll();
    }

    @Override
    public Optional<Proveedor> buscarProveedorPorId(Long id) {
        log.info("Buscando proveedor por ID: {}", id);
        return proveedorRepository.findById(id);
    }

    @Override
    public Optional<Proveedor> buscarProveedorPorNombre(String nombre) {
        log.info("Buscando proveedor por nombre: {}", nombre);
        return proveedorRepository.findByNombre(nombre);
    }

    @Override
    public Optional<Proveedor> buscarProveedorPorEmail(String email) {
        log.info("Buscando proveedor por email: {}", email);
        return proveedorRepository.findByEmail(email);
    }

    @Override
    public Optional<Proveedor> buscarProveedorPorCelular(Integer celular) {
        log.info("Buscando proveedor por celular: {}", celular);
        return proveedorRepository.findByCelular(celular);
    }

    @Override
    public Proveedor agregarProveedor(ProveedorDTO proveedorDTO) {
        log.info("Agregando nuevo proveedor: {}", proveedorDTO);
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(proveedorDTO.getNombre());
        proveedor.setDireccion(proveedorDTO.getDireccion());
        proveedor.setEmail(proveedorDTO.getEmail());
        proveedor.setCelular(proveedorDTO.getCelular());
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor actualizarProveedor(Long id, ProveedorDTO proveedorDTO) {
        log.info("Actualizando proveedor con ID {}: {}", id, proveedorDTO);
        Optional<Proveedor> optionalProveedor = proveedorRepository.findById(id);
        if (optionalProveedor.isPresent()) {
            Proveedor proveedor = optionalProveedor.get();
            proveedor.setNombre(proveedorDTO.getNombre());
            proveedor.setDireccion(proveedorDTO.getDireccion());
            proveedor.setEmail(proveedorDTO.getEmail());
            proveedor.setCelular(proveedorDTO.getCelular());
            return proveedorRepository.save(proveedor);
        } else {
            log.warn("Proveedor con ID {} no encontrado", id);
            throw new EntityNotFoundException("Proveedor no encontrado con ID: " + id);
        }
    }

    @Override
    public void borrarProveedor(Long id) {
        log.info("Borrando proveedor con ID: {}", id);
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
        } else {
            log.warn("Proveedor con ID {} no encontrado", id);
            throw new EntityNotFoundException("Proveedor no encontrado con ID: " + id);
        }
    }
}
