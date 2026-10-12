package com.utn.desarrollosoft.contable.entidades.cliente;

import com.utn.desarrollosoft.contable.entidades.base.EntityId;
import lombok.Data;

@Data
public class Domicilio extends EntityId {
    private String nombreCalle;
    private String numeroCalle;
}