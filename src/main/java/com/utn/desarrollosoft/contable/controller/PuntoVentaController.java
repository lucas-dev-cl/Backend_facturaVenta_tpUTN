package com.utn.desarrollosoft.contable.controller;

import com.utn.desarrollosoft.contable.entidades.dto.PuntoVentaResponse;
import com.utn.desarrollosoft.contable.servicios.PuntoVentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/puntos-venta")
public class PuntoVentaController {

    private final PuntoVentaService puntosVentaService;

    public PuntoVentaController(PuntoVentaService puntosVentaService) {
        this.puntosVentaService = puntosVentaService;
    }

    @GetMapping
    public ResponseEntity<List<PuntoVentaResponse>> listarPuntosVenta() {
        return ResponseEntity.ok().body(puntosVentaService.getPuntosVenta());
    }
}
