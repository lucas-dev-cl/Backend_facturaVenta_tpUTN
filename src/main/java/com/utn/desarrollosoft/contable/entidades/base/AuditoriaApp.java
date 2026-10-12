package com.utn.desarrollosoft.contable.entidades.base;

import com.utn.desarrollosoft.contable.entidades.usuario.Usuario;
import lombok.Data;

import java.util.Date;

@Data
public abstract class AuditoriaApp extends EntityId {
    protected Date fechaAlta;
    protected Date fechaBaja;
    protected Date fechaModificacion;
    protected Usuario usuarioCarga;
    protected Usuario usuarioBaja;
    protected Usuario usuarioModificacion;
}