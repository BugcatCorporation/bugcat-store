package com.bugcatcorp.app_bugcat_store.controller;

import com.bugcatcorp.app_bugcat_store.repository.RolRepository;
import com.bugcatcorp.app_bugcat_store.repository.UsuarioRepository;
import com.bugcatcorp.app_bugcat_store.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Validated
@Slf4j
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;

}

