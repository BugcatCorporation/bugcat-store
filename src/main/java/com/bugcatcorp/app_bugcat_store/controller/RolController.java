package com.bugcatcorp.app_bugcat_store.controller;

import com.bugcatcorp.app_bugcat_store.model.entity.Rol;
import com.bugcatcorp.app_bugcat_store.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
public class RolController {
    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<List<Rol>> findAll(){
        List<Rol> roles = rolService.listarRoles();

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

}