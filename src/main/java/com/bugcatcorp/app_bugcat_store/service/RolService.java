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


}

