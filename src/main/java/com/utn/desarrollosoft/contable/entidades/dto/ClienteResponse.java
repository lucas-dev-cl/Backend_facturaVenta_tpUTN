package com.utn.desarrollosoft.contable.entidades.dto;

import lombok.Builder;

@Builder
public record ClienteResponse(
        Integer id,
        String cuitCuil,
        String denominacion,
        CondicionIvaResponse condicionIva,
        DomicilioResponse domicilio,
        ContactoResponse contacto
) {
}
