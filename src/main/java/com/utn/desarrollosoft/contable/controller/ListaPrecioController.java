package com.utn.desarrollosoft.contable.controller;

import com.utn.desarrollosoft.contable.entidades.dto.ListaPrecioResponse;
import com.utn.desarrollosoft.contable.servicios.ListaPrecioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lista-precios")
public class ListaPrecioController {
    private final ListaPrecioService listaPrecioService;

    public ListaPrecioController(ListaPrecioService listaPrecioService) {
        this.listaPrecioService = listaPrecioService;
    }

    @GetMapping
    public ResponseEntity<List<ListaPrecioResponse>> listarListasPrecio() {
        return ResponseEntity.ok().body(listaPrecioService.getListaPrecios());
    }

}
