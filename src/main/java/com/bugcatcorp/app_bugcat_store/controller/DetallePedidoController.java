package com.bugcatcorp.app_bugcat_store.controller;

import com.bugcatcorp.app_bugcat_store.model.dto.DetallePedidoCreacionDTO;
import com.bugcatcorp.app_bugcat_store.model.dto.DetallePedidoDTO;
import com.bugcatcorp.app_bugcat_store.service.IDetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detallepedido")
public class DetallePedidoController {

    @Autowired
    private IDetallePedidoService detallePedidoService;

    @PostMapping
    public DetallePedidoDTO createDetallePedido(@RequestBody DetallePedidoCreacionDTO detallePedidoDTO) {
        return detallePedidoService.createDetallePedido(detallePedidoDTO);
    }

    @GetMapping("/{id}")
    public DetallePedidoDTO getDetallePedidoById(@PathVariable Long id) {
        return detallePedidoService.getDetallePedidoById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public DetallePedidoDTO updateDetallePedido(@PathVariable Long id, @RequestBody DetallePedidoDTO detallePedidoDTO) {
        return detallePedidoService.updateDetallePedido(id, detallePedidoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteDetallePedido(@PathVariable Long id) {
        detallePedidoService.deleteDetallePedido(id);
    }

    @GetMapping
    public List<DetallePedidoDTO> listAllDetallePedidos() {
        return detallePedidoService.listAllDetallePedidos();
    }
}
