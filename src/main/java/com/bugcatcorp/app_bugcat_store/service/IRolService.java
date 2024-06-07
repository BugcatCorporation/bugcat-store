package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.model.dto.RolDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolService {
    List<Rol> listarRoles();
    Optional<Rol> buscarRolPorId(Long id);
    Optional<Rol> buscarRolPorNombre(String rol);
    Rol agregarRol(RolDTO rolDTO);
    Rol actualizarRol(Long id, RolDTO rolDTO);
    void borrarRol(Long id);
}
