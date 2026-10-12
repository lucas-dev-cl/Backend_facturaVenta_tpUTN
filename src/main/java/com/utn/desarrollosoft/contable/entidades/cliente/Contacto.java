package com.utn.desarrollosoft.contable.entidades.cliente;

import com.utn.desarrollosoft.contable.entidades.base.EntityId;
import lombok.Data;

@Data
public class Contacto extends EntityId {
    private String email;
    private String telefono;
    private String celular;
}