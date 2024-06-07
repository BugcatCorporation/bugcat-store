package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.ProveedorDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Proveedor;

import java.util.List;
import java.util.Optional;

public interface IProveedorService {
    List<Proveedor> listarProveedores();
    Optional<Proveedor> buscarProveedorPorId(Long id);
    Optional<Proveedor> buscarProveedorPorNombre(String nombre);
    Optional<Proveedor> buscarProveedorPorEmail(String email);
    Optional<Proveedor> buscarProveedorPorCelular(Integer celular);
    Proveedor agregarProveedor(ProveedorDTO proveedorDTO);
    Proveedor actualizarProveedor(Long id, ProveedorDTO proveedorDTO);
    void borrarProveedor(Long id);
}
