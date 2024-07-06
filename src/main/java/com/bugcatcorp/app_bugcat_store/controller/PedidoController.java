package com.bugcatcorp.app_bugcat_store.controller;

import com.bugcatcorp.app_bugcat_store.model.dto.PedidoCreacionDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.PedidoDTO;
import com.bugcatcorp.app_bugcat_store.service.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;

    @PostMapping("")
    public ResponseEntity<PedidoDTO> agregarPedido(@RequestBody PedidoCreacionDTO pedidoCreacionDTO) {
        PedidoDTO pedidoDTO = pedidoService.agregarPedido(pedidoCreacionDTO);
        return ResponseEntity.ok(pedidoDTO);
    }


}
