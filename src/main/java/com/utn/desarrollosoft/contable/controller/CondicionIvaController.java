package com.utn.desarrollosoft.contable.controller;

import com.utn.desarrollosoft.contable.entidades.dto.CondicionIvaResponse;
import com.utn.desarrollosoft.contable.servicios.CondicionIvaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/condiciones-iva")
public class CondicionIvaController {
    private final CondicionIvaService condicionIvaService;

    public CondicionIvaController(CondicionIvaService condicionIvaService) {
        this.condicionIvaService = condicionIvaService;
    }

    @GetMapping
    public ResponseEntity<List<CondicionIvaResponse>> listarCondicionIvas() {
        return ResponseEntity.ok().body(condicionIvaService.getCondicionIvas());
    }
}
