package com.utn.desarrollosoft.contable.servicios;

import com.utn.desarrollosoft.contable.entidades.dto.PuntoVentaResponse;
import com.utn.desarrollosoft.contable.repository.PuntoVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuntoVentaService {
    private final PuntoVentaRepository puntoVentaRepository;

    public PuntoVentaService(PuntoVentaRepository puntoVentaRepository) {
        this.puntoVentaRepository = puntoVentaRepository;
    }

    /**
     * Mapeamos cada punto de venta en un puntoVentaResponse
     *
     * @return lista de puntos de venta
     */
    public List<PuntoVentaResponse> getPuntosVenta() {
        return puntoVentaRepository.findAll().stream()
                .map(puntoVenta -> PuntoVentaResponse.builder()
                        .id(Math.toIntExact(puntoVenta.getId()))
                        .descripcion(puntoVenta.getDescripcion())
                        .tipoEmision(puntoVenta.getTipoEmision())
                        .domicilioComercial(puntoVenta.getDomicilioComercial())
                        .numero(puntoVenta.getNumero())
                        .build())
                .toList();
    }
}
