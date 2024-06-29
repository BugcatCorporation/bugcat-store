package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.ProveedorCreacionDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.ProveedorDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Proveedor;

import java.util.List;
import java.util.Optional;

public interface IProveedorService {
    List<ProveedorDTO> listarProveedores();
    Optional<ProveedorDTO> obtenerProveedorPorId(Long id);
    ProveedorDTO agregarProveedor(ProveedorCreacionDTO proveedorCreacionDTO);
    ProveedorDTO actualizarProveedor(Long id, ProveedorDTO proveedorDTO);
}
