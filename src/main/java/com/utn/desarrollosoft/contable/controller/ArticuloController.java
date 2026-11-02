package com.utn.desarrollosoft.contable.controller;

import com.utn.desarrollosoft.contable.entidades.dto.ArticuloResponse;
import com.utn.desarrollosoft.contable.servicios.ArticuloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/articulo")
public class ArticuloController {
    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService){
        this.articuloService = articuloService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ArticuloResponse>> searchArticulos(@RequestParam String denominacion){
        return ResponseEntity.ok().body(articuloService.buscarArticuloPorDenominacion(denominacion));
    }
}
