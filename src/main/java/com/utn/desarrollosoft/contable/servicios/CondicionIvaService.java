package com.utn.desarrollosoft.contable.servicios;

import com.utn.desarrollosoft.contable.entidades.dto.CondicionIvaResponse;
import com.utn.desarrollosoft.contable.repository.CondicionIvaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CondicionIvaService {
    private final CondicionIvaRepository condicionIvaRepository;

    public CondicionIvaService(CondicionIvaRepository condicionIvaRepository){
        this.condicionIvaRepository = condicionIvaRepository;
    }

    /**
     * Retorna una lista de todas las condiciones de IVA
     *
     * @return lista de condiciones de IVA
     */
    public List<CondicionIvaResponse> getCondicionIvas() {
        return condicionIvaRepository.findAll().stream()
                .map(condicionIva -> CondicionIvaResponse.builder()
                        .id(Math.toIntExact(condicionIva.getId()))
                        .denominacion(condicionIva.getDenominacion())
                        .codigoAfip(condicionIva.getCodigoAfip())
                        .build())
                .toList();
    }
}
