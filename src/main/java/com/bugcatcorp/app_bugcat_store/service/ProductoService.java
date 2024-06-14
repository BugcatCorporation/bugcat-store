package com.bugcatcorp.app_bugcat_store.service;

import com.bugcatcorp.app_bugcat_store.exception.EntityNotFoundException;
import com.bugcatcorp.app_bugcat_store.model.dto.ProductoDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.ProductoUpdateDTO;
import com.bugcatcorp.app_bugcat_store.model.entity.Producto;
import com.bugcatcorp.app_bugcat_store.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ProductoService implements IProductoService {

    private final ProductoRepository pr;


}
