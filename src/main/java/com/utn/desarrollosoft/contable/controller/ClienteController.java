package com.utn.desarrollosoft.contable.controller;

import com.utn.desarrollosoft.contable.entidades.dto.ClienteResponse;
import com.utn.desarrollosoft.contable.servicios.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> getClientes() {
        return ResponseEntity.ok().body(clienteService.getClientes());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteResponse>> searchClientes(@RequestParam String denominacion) {
        return ResponseEntity.ok().body(clienteService.searchClientesPorDenominacion(denominacion));
    }
}
