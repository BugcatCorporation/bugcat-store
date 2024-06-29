package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.exception.EntityNotFoundException;
import com.bugcatcorp.app_bugcat_store.model.dto.ProveedorCreacionDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.ProveedorDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Proveedor;
import com.bugcatcorp.app_bugcat_store.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorService implements IProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<ProveedorDTO> listarProveedores() {
        return proveedorRepository.findAll().stream()
                .map(this::convertirAProveedorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProveedorDTO> obtenerProveedorPorId(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con el id " + id));
        return Optional.of(convertirAProveedorDTO(proveedor));
    }

    @Override
    public ProveedorDTO agregarProveedor(ProveedorCreacionDTO proveedorCreacionDTO) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(proveedorCreacionDTO.getNombre());
        proveedor.setDireccion(proveedorCreacionDTO.getDireccion());
        proveedor.setEmail(proveedorCreacionDTO.getEmail());
        proveedor.setCelular(proveedorCreacionDTO.getCelular());
        proveedor = proveedorRepository.save(proveedor);
        return convertirAProveedorDTO(proveedor);
    }

    @Override
    public ProveedorDTO actualizarProveedor(Long id, ProveedorDTO proveedorDTO) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con el id " + id));
        proveedor.setNombre(proveedorDTO.getNombre());
        proveedor.setDireccion(proveedorDTO.getDireccion());
        proveedor.setEmail(proveedorDTO.getEmail());
        proveedor.setCelular(proveedorDTO.getCelular());
        proveedor = proveedorRepository.save(proveedor);
        return convertirAProveedorDTO(proveedor);
    }

    private ProveedorDTO convertirAProveedorDTO(Proveedor proveedor) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setNombre(proveedor.getNombre());
        dto.setDireccion(proveedor.getDireccion());
        dto.setEmail(proveedor.getEmail());
        dto.setCelular(proveedor.getCelular());
        return dto;
    }
}
